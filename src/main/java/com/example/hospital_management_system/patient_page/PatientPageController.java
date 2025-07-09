package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class PatientPageController {

    public VBox slider;
    @FXML
    private BorderPane bp;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button appointmentsButton;
    @FXML
    private Button historyButton;
    @FXML
    private Main main;

    private void loadPage(String page) throws IOException {
        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        bp.setCenter(root);
    }

    public void patient_dashboard(MouseEvent mouseEvent) {
        try {
            loadPage("patient_dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void patient_appointments(MouseEvent mouseEvent) {
        try {
            loadPage("patient_appointments");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void patient_history(MouseEvent mouseEvent) {
        try {
            loadPage("patient_history");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
