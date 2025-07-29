package com.example.hospital_management_system;

import com.example.hospital_management_system.Networking.Client;
import com.example.hospital_management_system.Staff.StaffMap;
import com.example.hospital_management_system.admin_page.Admin;
import com.example.hospital_management_system.admin_page.AdminMap;
import com.example.hospital_management_system.admin_page.AdminPageController;
import com.example.hospital_management_system.appointment_system.*;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.doctor_page.DoctorPageController;
import com.example.hospital_management_system.doctor_page.DoctorsMap;
import com.example.hospital_management_system.doctor_page.ResidentPage;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientChatMap;
import com.example.hospital_management_system.patient_page.PatientPageController;
import com.example.hospital_management_system.patient_page.PatientsMap;
import com.example.hospital_management_system.register_page.RegistrationController;
import com.example.hospital_management_system.Staff.*;
import com.example.hospital_management_system.register_page.SuccessPage;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Stage stage;
    Stage currentStage;
    public static PatientsMap patientsMap;
    public static DoctorsMap doctorsMap;
    public static AdminMap adminMap;
    public static AppointmentMap appointmentMap;
    public static PatientChatMap patientChatMap;
    public static StaffMap staffMap;
    public static PrescriptionMap prescriptionMap;
    public static Integer[] roomNos = new Integer[12];
    public static Client c;
    public static final BooleanProperty updated = new SimpleBooleanProperty(false);

    public static BooleanProperty isUpdatedProperty() {
        return updated;
    }

    // Static setter
    public static void setUpdated(boolean value) {
        updated.set(value);
    }

    // Static getter
    public static boolean getUpdated() {
        return updated.get();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        c = new Client(serverAddress, serverPort, "Main1");
        c.setType("Main");
        c.setObType((Object) this);
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("new_login_page.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMain(this);
        for (int i = 1; i < 12; i++) {
            roomNos[i] = 100 * i + 5;
        }

        loadPatients();
        loadDoctors();
        loadAppointments();
        loadAdmins();
        loadPatientChats();
        loadStaff();
        loadPrescriptions();


        stage.setTitle("Hospital Management System");
        stage.setScene(new Scene(root, 400, 700));
        stage.show();
    }



    public void showHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        HelloController controller = loader.getController();
        controller.setMain(this);

        stage.getScene().setRoot(root);
    }

    public void showSuccessPage(String msg) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register_page/success_Page.fxml"));
        Parent root = loader.load();

        SuccessPage controller = loader.getController();
        controller.setMain(this);
        controller.setSuccessMessage(msg);


        Stage newStage =  new Stage();
        newStage.setScene(new Scene(root, 1280, 720));
        newStage.show();
        stage.close();
    }

    public void showPatientPage(Patient p) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("patient_page/patient_page.fxml"));
        Parent root = loader.load();

        PatientPageController controller = loader.getController();
        controller.setMain(this);
        controller.setPatient(p);

        Stage newStage =  new Stage();
        newStage.setScene(new Scene(root, 1280, 720));
        newStage.show();
        stage.close();
    }

    public void showDoctorPage(Doctor d) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("doctor_page/doctor_page.fxml"));
        Parent root = loader.load();

        DoctorPageController controller = loader.getController();
        controller.setMain(this);
        controller.setDoctor(d);

        Stage newStage =  new Stage();
        newStage.setScene(new Scene(root, 1280, 720));
        newStage.show();
        stage.close();
    }

    public void showRegisterPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register_page/register_page.fxml"));
        Parent root = loader.load();

        stage.setWidth(1280);
        stage.setHeight(720);
        RegistrationController controller = loader.getController();
        controller.setMain(this);


        Stage newStage =  new Stage();
        newStage.setScene(new Scene(root, 1280, 720));
        newStage.show();
        stage.close();
    }
    public void showAdminPage(Admin a) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("admin_page/admin_page.fxml"));
        Parent root = loader.load();

        AdminPageController controller = loader.getController();
        controller.setMain(this);
        System.out.println(a.getImage());
        controller.setAdmin(a);

        Stage newStage =  new Stage();
        newStage.setScene(new Scene(root, 1280, 720));
        newStage.show();
        stage.close();
    }
    public void showResidentPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("doctor_page/resident_page.fxml"));
        Parent root = loader.load();

        ResidentPage controller = loader.getController();
        controller.setMain(this);
        controller.initializeManually();

        Stage newStage =  new Stage();
        newStage.setScene(new Scene(root, 1280, 720));
        newStage.show();
        stage.close();
    }

    public void loadPatients() throws IOException {
        patientsMap = new PatientsMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null || line.length()<24) break;
            String [] values = line.split("\\|");
            System.out.println("Values:"+values.length);
            Patient p = new Patient(values[0], values[1], Integer.parseInt(values[2]), values[3], Float.parseFloat(values[4]), Float.parseFloat(values[5]), values[6],
                    Integer.parseInt(values[7]), Integer.parseInt(values[8]), values[9], Boolean.parseBoolean(values[10]), Boolean.parseBoolean(values[11]), Boolean.parseBoolean(values[12]),
                    Boolean.parseBoolean(values[13]), Boolean.parseBoolean(values[14]), Boolean.parseBoolean(values[15]), Boolean.parseBoolean(values[16]), Boolean.parseBoolean(values[17]),
                    Boolean.parseBoolean(values[18]), Boolean.parseBoolean(values[19]), Boolean.parseBoolean(values[20]), Boolean.parseBoolean(values[21]), values[22]);
            p.setPassAndId(values[24], values[23]);
            p.setTestReportNumbers(Integer.parseInt(values[25]));
            String userAndPass = values[23] + "@" + values[24];
            patientsMap.addPatient(p);
            System.out.println();
        }
        br.close();
    }

    public void loadAdmins() throws IOException {
        adminMap = new AdminMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/AdminsList.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("<");
            Admin a = new Admin(values[0], values[1], values[2], values[3], values[4], values[5]);
            adminMap.addAdmin(a);
        }
        br.close();
    }

    public void loadDoctors() throws IOException {
        doctorsMap = new DoctorsMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/DoctorsList.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("\\|");
            Doctor d = new Doctor(values[0], values[1], Integer.parseInt(values[2]), values[3], values[4], values[5], values[6], values[7], values[8],values[9], values[10],Integer.parseInt(values[11]), values[12], Integer.parseInt(values[13]),
                    values[14],values[15]);
            d.setUserNameAndPass(values[16], values[17]);
            String userAndPass = values[16] + "@" + values[17];
            doctorsMap.addDoctor(d);
            System.out.println();
        }
        br.close();
    }

    public void loadAppointments() throws IOException {
        appointmentMap = new AppointmentMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/AppointmentList.txt"));
        List<String> lines = new ArrayList<>();
        int i=0;
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("<");
            String[] temp = values[0].split("\\(");
            String[] temp2 = temp[1].split("\\)");
            String doctorId = temp2[0];
            String patientId = values[7];
//            System.out.println(doctorId);
//            System.out.println(patientId);
            i++;
            if(Main.patientsMap.getPatientById(patientId) == null || Main.doctorsMap.getDoctorById(doctorId) == null){ continue;}
            Appointment a = new Appointment(values[0], values[1], values[2], Integer.parseInt(values[3]), values[4], values[5], Integer.parseInt(values[6]), values[7]);
            appointmentMap.addAppointment(a);
            lines.add(line);
            System.out.println(line);
            //System.out.println();
        }
        br.close();
        if(lines.size()<i) {
            System.out.println(lines.size());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/AppointmentList.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }

    public void loadStaff() throws IOException {
        staffMap = new StaffMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/StaffList.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("\\|");

            String type = values[0];
            String name = values[1];
            String dept = values[2];
            String years = values[3];
            String phone = values[4];
            String email = values[5];
            String address = values[6];

            if (type.equals("Nurse")) {
                staffMap.addNurse(new Nurse(name, dept, years, phone, email, address));

            }
            else if (type.equals("BedBoy")) {
                staffMap.addBedBoy(new BedBoy(name, dept, years, phone, email, address));
            }
            else if (type.equals("Receptionist")) {
                staffMap.addReceptionist(new Receptionist(name, dept, years, phone, email, address));
            }

        }
        br.close();
    }

    public void loadPrescriptions() throws IOException {
        prescriptionMap = new PrescriptionMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/PrescriptionList.txt"));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("\\|");
            //READ PRESCRIPTION AND LOAD IT INTO THE MAP
            Prescription p = new Prescription(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);

            //THIS CODE CAN BE IMPROVED -> JUST KEEP EMPTY STRING IF RMXi  == empty
            p.setRemedy1(values[7]);
            p.setRemedy2(values[8]);
            p.setRemedy3(values[9]);
            p.setRemedy4(values[10]);
            p.setRemedy5(values[11]);
            p.setRemedy6(values[12]);

            prescriptionMap.addPrescription(p);
        }
        br.close();
    }

    public void loadPatientChats() throws IOException {
        patientChatMap = new PatientChatMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/ChatHistoryOfPatients.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null || line.length()<5) break;
            String [] values = line.split("\\|");
            if(values.length<2){break;}
            //System.out.println("Values: "+values.length);
            System.out.println(values[1]);
            Patient p = patientsMap.getPatient(values[0]);
            p.setMyChat(values[1]);
            patientChatMap.addChat(p, values[1]);
        }
        br.close();
    }

    public static boolean showLogOutAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to logout?");
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType2 = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType, buttonType2);
        alert.showAndWait();
        return alert.getResult() != buttonType2;
    }

    public static void main(String[] args) throws IOException{
        launch(args);
    }

}
