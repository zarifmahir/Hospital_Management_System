package com.example.hospital_management_system.Networking;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.admin_page.AdminPageController;
import com.example.hospital_management_system.admin_page.PatientPanelController;
import com.example.hospital_management_system.doctor_page.ChatOfDoctorController;
import com.example.hospital_management_system.doctor_page.ResidentPage;
import com.example.hospital_management_system.patient_page.ChatOfPatientController;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientPageController;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    //private String[] fnames = {"AdminsList.txt", "AppointmentList.txt", "ChatHistoryOfPatients.txt", "DoctorsList.txt", "Numbers.txt", "PatientsList.txt", "StaffList.txt"};

    public ReadThreadClient(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if(socketWrapper.getType().equals("Main")){
                    String[] spt = ((String) o).split("\\$");
                    if(spt[0].equals("FileWriting")){
                        Object o2 = socketWrapper.read();
                        byte[] b = (byte[]) o2;
                        try (FileOutputStream fos = new FileOutputStream("src/main/resources/pdfs/"+spt[1])) {
                            fos.write(b);
                        }

                        String[] te = spt[1].split("_");
                        String id = te[0];
                        String newLine = "";
                        String oldLine = "";
                        List<String> lines = new ArrayList<>();
                        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                                lines.add(line);
                                String[] s =  line.split("\\|");
                                if(s[0].equals(id)){
                                    int tr = Main.patientsMap.getPatientById(id).getTestReportNumbers();
                                    Main.patientsMap.getPatientById(id).setTestReportNumbers(tr+1);
                                    s[25]=String.valueOf(tr+1);
                                    for(int i=0; i<25; i++) newLine += s[i]+"|";
                                    newLine+=s[25];
                                    oldLine = line;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }

//                System.out.println(lines.size());
//                System.out.println(serial);
//
//                lines.remove(serial);


                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/PatientsList.txt"))) {
                            for (String line : lines) {
                                if(line.equals(oldLine)) writer.write(newLine);
                                else writer.write(line);
                                writer.newLine();
                            }
                        }

                        Main main = (Main)socketWrapper.getO();
                        main.loadPatients();
                        Main.setUpdated(!Main.getUpdated());

                        System.out.println("File received and saved as received_" + spt[1]);
                    }
                    else if(spt[0].equals("Numbers")) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/"+spt[0]+".txt"));
                        writer.write(spt[1]);
                        writer.close();
                    }
                    else{
                        BufferedWriter writer;
                        System.out.println((String)o);
                        if(spt[1].equals("Remove")){
                            List<String> lines = new ArrayList<>();
                            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/"+ spt[0]+ ".txt"))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    System.out.println(line);
                                    lines.add(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }

                            lines.remove(Integer.parseInt(spt[2]));
                                writer = new BufferedWriter(new FileWriter("src/main/resources/texts/"+spt[0]+ ".txt"));
                                for (String line : lines) {
                                    writer.write(line);
                                    writer.newLine();
                                }

                        }
                        else if(spt[1].equals("EmptyChat")) {
                            writer = new BufferedWriter(new FileWriter("src/main/resources/texts/"+spt[0]+ ".txt"));
                            writer.write("");
                        }

                        else if(spt[1].equals("ChatRefreshed")) {
                            writer = new BufferedWriter(new FileWriter("src/main/resources/texts/"+spt[0]+ ".txt", true));
                            Main main = (Main)socketWrapper.getO();
                            main.loadPatientChats();
                        }

                        else {
                            writer = new BufferedWriter(new FileWriter("src/main/resources/texts/" + spt[0] + ".txt", true));
                            writer.write(spt[1]);
                            writer.newLine();
                        }
                        writer.close();

                        if(spt[0].equals("PatientsList")) {
                            Main main = (Main)socketWrapper.getO();
                            main.loadPatients();
                            Main.setUpdated(!Main.getUpdated());
                        }
                        else if(spt[0].equals("DoctorsList")) {
                            Main main = (Main)socketWrapper.getO();
                            main.loadDoctors();
                            Main.setUpdated(!Main.getUpdated());
                        }
                        else if(spt[0].equals("AppointmentList")) {
                            Main main = (Main)socketWrapper.getO();
                            main.loadAppointments();
                            Main.setUpdated(!Main.getUpdated());
                        }

                    }

                }
                else if (o instanceof Message) {
                    Message obj = (Message) o;
                    System.out.println(obj.getFrom() + ", " + obj.getTo() + ", " + obj.getText());
                }
                else if (o instanceof String && !((String) o).isEmpty()) {

                    if(socketWrapper.getType().equals("Patient")) {
                        String incoming = (String) o;
                        if(incoming.equals("#Refresh")) {
                            PatientPageController.reloadPatientChats((Patient) socketWrapper.getO());
//                            socketWrapper.write("#RefreshedSuccessfully");
                        }
                        else ChatOfPatientController.addLabel((String) o, socketWrapper.getvBoxOfMessages(), socketWrapper.getO());
                    }
                    else if(socketWrapper.getType().equals("Resident")) {
                        String incoming = (String) o;
                        String[] s = incoming.split("\\|");
                        System.out.println(s[0]);
                        System.out.println(s[1]);
                        if(incoming.startsWith("New")) {
                            ResidentPage obj = (ResidentPage) socketWrapper.getO();
                            obj.getMain().loadPatientChats();
                            System.out.println("working");
                            Platform.runLater(() -> {
                               obj.initManual();
                            });
                        }
                        else ResidentPage.addLabel(s[1], s[0], socketWrapper.getvBoxOfMessages(), Main.patientsMap.getPatientByname(s[1]));
                    }
                    else ChatOfDoctorController.addLabel(socketWrapper.getName(), (String)o, socketWrapper.getvBoxOfMessages());
                   // System.out.println("Hello");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
