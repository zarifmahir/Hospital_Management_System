package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class DoctorPageController {

    @FXML
    private BorderPane doctorBorderPane;

    @FXML
    private Button dashboardButton;

    @FXML
    private Label logoutButton;

    @FXML
    private Label menuButton;

    @FXML
    private Button patientsButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private VBox slider;

    @FXML
    private Main main;

    @FXML
    private void loadPage(String page) throws IOException {
        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        doctorBorderPane.setCenter(root);
    }

    @FXML
    void doctor_dashboard(MouseEvent event) {
        try {
            loadPage("doctor_dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void doctor_patient_history(MouseEvent event) {
        try {
            loadPage("doctor_patient_history");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void doctor_schedule(MouseEvent event) {
        try {
            loadPage("doctor_schedule");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOut(ActionEvent event) throws Exception {
        main.showLoginPage();
    }

    @FXML
    public void setMain(Main main) {
        this.main = main;
    }

}
