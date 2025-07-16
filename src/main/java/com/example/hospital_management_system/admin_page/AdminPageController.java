package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.DoctorDashboardController;
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

public class AdminPageController {

    @FXML
    private Button adminDashboardButton;

    @FXML
    private BorderPane bp;

    @FXML
    private Button doctorsPanelButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label menuButton2;

    @FXML
    private Button reportsButton;

    @FXML
    private VBox slider;

    @FXML
    private Button systemSettingsButton;

    @FXML
    private Button userManagementButton;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    private void loadPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();

        bp.setCenter(root);
    }

    @FXML
    void adminDashboard(ActionEvent event) {

    }

    @FXML
    void doctorsPanel(ActionEvent event) {
        try {
            loadPage("doctors_panel");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            main.showLoginPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void patient_appointments(MouseEvent event) {

    }

    @FXML
    void patient_dashboard(MouseEvent event) {

    }

    @FXML
    void patient_history(MouseEvent event) {

    }

    @FXML
    void reports(ActionEvent event) {

    }

    @FXML
    void setOnMousePressed(MouseEvent event) {

    }

    @FXML
    void setOnMousePressed1(MouseEvent event) {

    }

    @FXML
    void setOnMousePressed2(MouseEvent event) {

    }

    @FXML
    void setOnMousePressed3(MouseEvent event) {

    }

    @FXML
    void setOnMousePressed4(MouseEvent event) {

    }

    @FXML
    void setOnMouseReleased(MouseEvent event) {

    }

    @FXML
    void setOnMouseReleased1(MouseEvent event) {

    }

    @FXML
    void setOnMouseReleased2(MouseEvent event) {

    }

    @FXML
    void setOnMouseReleased4(MouseEvent event) {

    }

    @FXML
    void systemSettings(ActionEvent event) {

    }

    @FXML
    void userManagement(ActionEvent event) {

    }

}
