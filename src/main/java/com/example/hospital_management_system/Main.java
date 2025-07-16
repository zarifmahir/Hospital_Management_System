package com.example.hospital_management_system;

import com.example.hospital_management_system.admin_page.AdminPageController;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.doctor_page.DoctorPageController;
import com.example.hospital_management_system.doctor_page.DoctorsMap;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientPageController;
import com.example.hospital_management_system.patient_page.PatientsMap;
import com.example.hospital_management_system.register_page.RegistrationController;
import com.example.hospital_management_system.register_page.SuccessPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {
    Stage stage;
    public static PatientsMap patientsMap;
    public static DoctorsMap doctorsMap;


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login_page.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMain(this);
        loadPatients();
        loadDoctors();

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
    public void showAdminPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("admin_page/admin_page.fxml"));
        Parent root = loader.load();

        AdminPageController controller = loader.getController();
        controller.setMain(this);

        stage.getScene().setRoot(root);
    }

    public void loadPatients() throws IOException {
        patientsMap = new PatientsMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("<");
            System.out.println("Values:"+values.length);
            Patient p = new Patient(values[0], Integer.parseInt(values[1]), values[2], Float.parseFloat(values[3]), Float.parseFloat(values[4]), values[5],
                    Integer.parseInt(values[6]), Integer.parseInt(values[7]), values[8], Boolean.parseBoolean(values[9]), Boolean.parseBoolean(values[10]), Boolean.parseBoolean(values[11]),
                    Boolean.parseBoolean(values[12]), Boolean.parseBoolean(values[13]), Boolean.parseBoolean(values[14]), Boolean.parseBoolean(values[15]), Boolean.parseBoolean(values[16]),
                    Boolean.parseBoolean(values[17]), Boolean.parseBoolean(values[18]), Boolean.parseBoolean(values[19]), Boolean.parseBoolean(values[20]), values[21]);
            p.setPassAndId(values[23], values[22]);
            String userAndPass = values[22] + "@" + values[23];
            patientsMap.addPatient(p);
            System.out.println();
        }
        br.close();
    }

    public void loadDoctors() throws IOException {
        doctorsMap = new DoctorsMap();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/DoctorsList.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split("<");
            System.out.println("Values: "+values.length);
            Doctor d = new Doctor(values[0], Integer.parseInt(values[1]), values[2], values[3], values[4], values[5], values[6], values[7],values[8], values[9],Integer.parseInt(values[10]), values[11], Integer.parseInt(values[12]),
                    values[13],values[14]);
            d.setUserNameAndPass(values[15], values[16]);
            String userAndPass = values[15] + "@" + values[16];
            doctorsMap.addDoctor(d);
            System.out.println();
        }
        br.close();
    }

    public static void main(String[] args) throws IOException{
        launch(args);
    }

}
