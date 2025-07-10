package com.example.hospital_management_system;

import com.example.hospital_management_system.doctor_page.DoctorPageController;
import com.example.hospital_management_system.patient_page.PatientPageController;
import com.example.hospital_management_system.register_page.PatientRegister;
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

    public void showSuccessPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register_page/success_Page.fxml"));
        Parent root = loader.load();

        SuccessPage controller = loader.getController();
        controller.setMain(this);

        stage.getScene().setRoot(root);
    }

    public void showPatientPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("patient_page/patient_page.fxml"));
        Parent root = loader.load();

        PatientPageController controller = loader.getController();
        controller.setMain(this);
        stage.getScene().setRoot(root);
    }

    public void showDoctorPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("doctor_page/doctor_page.fxml"));
        Parent root = loader.load();

        DoctorPageController controller = loader.getController();
        controller.setMain(this);

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

    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"));
//        while (true) {
//        String line = br.readLine();
//        if (line == null) break;
//        String [] values = line.split(",");
////        Movie m = new Movie(values[0], Integer.parseInt(values[1]), values[2], values[3], values[4], Integer.parseInt(values[5]), values[6], Integer.parseInt(values[7]), Integer.parseInt(values[8]));
////        movieList.add(m)
//        // System.out.println();
//    }
   // br.close();
        launch(args);
    }

}
