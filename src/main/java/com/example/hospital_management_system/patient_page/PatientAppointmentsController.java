package com.example.hospital_management_system.patient_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PatientAppointmentsController {
    @FXML
    private BorderPane bp;


    private void loadPage(String page) throws IOException {
        bp.setCenter(null);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();


        bp.setCenter(root);
    }
    @FXML
    void showAppointmentPage(ActionEvent event) {
        try {
            loadPage("show_appointments_page");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showBookAppointmentPage(ActionEvent event) {
        try {
            loadPage("book_appointments_page");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDoctorPanelPage(ActionEvent event) {
        try {
            loadPage("show_doctor_panel");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}



