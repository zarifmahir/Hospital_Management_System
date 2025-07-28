package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.patient_page.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ShowPatientInformationController {
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private Label ageField;

    @FXML
    private ListView<?> complicationsListView;

    @FXML
    private Label emergencyContactField;

    @FXML
    private Label genderField;

    @FXML
    private Label heightField;

    @FXML
    private Label nameField;

    @FXML
    private Label phoneField;

    @FXML
    private Label primaryDiagnosisField;

    @FXML
    private Label weightField;

}
