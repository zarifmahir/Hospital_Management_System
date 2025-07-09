package com.example.hospital_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.Objects;

public class PatientPageController {

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
            throw new RuntimeException(e);
        }

        bp.setCenter(root);
    }

    public void patient_dashboard(MouseEvent mouseEvent) {
        try {
            loadPage("patient_dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void patient_appointments(MouseEvent mouseEvent) {
        try {
            loadPage("patient_appointments");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void patient_history(MouseEvent mouseEvent) {
        try {
            loadPage("patient_history");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
