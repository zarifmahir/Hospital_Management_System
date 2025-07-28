package com.example.hospital_management_system.Networking;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.ChatOfDoctorController;
import com.example.hospital_management_system.doctor_page.ResidentPage;
import com.example.hospital_management_system.patient_page.ChatOfPatientController;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientPageController;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;

    public ReadThreadClient(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o instanceof Message) {
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
                        else ResidentPage.addLabel(s[1], s[0], socketWrapper.getvBoxOfMessages());
                    }
                    else ChatOfDoctorController.addLabel(socketWrapper.getName(), (String)o, socketWrapper.getvBoxOfMessages());
                    System.out.println("Hello");
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
