package com.example.hospital_management_system.patient_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PatientAppointmentsController {
    public Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private BorderPane bp;


    private void loadPage(String page) throws IOException {
        bp.setCenter(null);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();

        if (page.equals("patient_appointments")) {
            PatientAppointmentsController controller = loader.getController();
            controller.setPatient(patient);
        }

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



