package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.patient_page.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddNewPrescriptionController {
    public Patient patient;
    public Doctor doctor;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @FXML
    private Button confirmButton;

    @FXML
    private TextField dateField;

    @FXML
    private TextField diagnosisField;

    @FXML
    private TextField remedyField1;

    @FXML
    private TextField remedyField2;

    @FXML
    private TextField remedyField3;

    @FXML
    private TextField remedyField4;

    @FXML
    private TextField remedyField5;

    @FXML
    private TextField remedyField6;

    @FXML
    private Label unfilledLabelField;

    public void initialize() {
        unfilledLabelField.setVisible(false);
        dateField.setEditable(false);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);

        dateField.setText(formattedDate);


    }

    @FXML
    void confirm(ActionEvent event) {
        //CLOSE CURRENT PAGE AND OPEN NEW PAGE
        //BUILD PRESCRIPTION
        //PUT PRESCRIPTION IN PRESCRIPTIONMAP
        //WRITE IT TO PRESCRIPTIONS.TXT

        if (remedyField1.getText() == null && remedyField2.getText() == null && remedyField3.getText() == null && remedyField4.getText() == null && remedyField5.getText() == null && remedyField6.getText() == null) {
            unfilledLabelField.setVisible(true);
        }
        else {
            unfilledLabelField.setVisible(false);
            buildPrescription();
        }
    }

    private void buildPrescription() {

    }

}
