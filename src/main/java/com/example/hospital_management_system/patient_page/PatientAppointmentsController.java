package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PatientAppointmentsController {
    @FXML
    private BorderPane bp;

    public Patient patient;

    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }


    private void loadPage(String page) throws IOException {
        bp.setCenter(null);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();

        if (page.equals("book_appointments_page")) {
            BookAppointmentsPage controller = loader.getController();
            controller.setPatient(patient);
        }
        else if (page.equals("show_appointments_page")) {
            ShowAppointmentsPage controller = loader.getController();
            controller.setPatient(patient);
            controller.setMain(main);
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

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}



