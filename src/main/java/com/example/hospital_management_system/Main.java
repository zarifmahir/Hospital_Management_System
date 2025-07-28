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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {
    Stage stage;
    public static PatientsMap patientsMap;
    public static DoctorsMap doctorsMap;
    public static AdminMap adminMap;
    public static AppointmentMap appointmentMap;
    public static PatientChatMap patientChatMap;
    public static StaffMap staffMap;
    public static Integer[] roomNos = new Integer[12];
    public static Client c;



    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        c = new Client(serverAddress, serverPort, "Main1");
        c.setType("Main");
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login_page.fxml"));
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


        stage.setTitle("Hospital Management System");
        stage.setScene(new Scene(root, 1280, 720));
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

        stage.getScene().setRoot(root);
    }

    public void showPatientPage(Patient p) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("patient_page/patient_page.fxml"));
        Parent root = loader.load();

        PatientPageController controller = loader.getController();
        controller.setMain(this);
        controller.setPatient(p);

        stage.getScene().setRoot(root);
    }

    public void showDoctorPage(Doctor d) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("doctor_page/doctor_page.fxml"));
        Parent root = loader.load();

        DoctorPageController controller = loader.getController();
        controller.setMain(this);
        controller.setDoctor(d);

        stage.getScene().setRoot(root);
    }

    public void showRegisterPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register_page/register_page.fxml"));
        Parent root = loader.load();

        RegistrationController controller = loader.getController();
        controller.setMain(this);


        stage.getScene().setRoot(root);
    }
    public void showAdminPage(Admin a) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("admin_page/admin_page.fxml"));
        Parent root = loader.load();

        AdminPageController controller = loader.getController();
        controller.setMain(this);
        System.out.println(a.getImage());
        controller.setAdmin(a);

        stage.getScene().setRoot(root);
    }
    public void showResidentPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("doctor_page/resident_page.fxml"));
        Parent root = loader.load();

        ResidentPage controller = loader.getController();
        controller.setMain(this);
        controller.initializeManually();

        stage.getScene().setRoot(root);

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
            a.display();
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
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("<");
            Appointment a = new Appointment(values[0], values[1], values[2], Integer.parseInt(values[3]), values[4], values[5], Integer.parseInt(values[6]));
            appointmentMap.addAppointment(a);
            System.out.println();
        }
        br.close();
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
                System.out.println("adding nurse" + name);
            }
            else if (type.equals("BedBoy")) {
                staffMap.addBedBoy(new BedBoy(name, dept, years, phone, email, address));
                System.out.println("adding r" + name);
            }
            else if (type.equals("Receptionist")) {
                staffMap.addReceptionist(new Receptionist(name, dept, years, phone, email, address));
                System.out.println("adding wb" + name);
            }

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
