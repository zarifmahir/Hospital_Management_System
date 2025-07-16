package com.example.hospital_management_system.patient_page;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PatientDashboardController {
    @FXML
    public ImageView patientPhoto;
    @FXML
    private Patient patient;

    @FXML
    private Label ageField;

    @FXML
    private Label bloodGroupField;

    @FXML
    private Label emailField;

    @FXML
    private Label emergencyContactField;

    @FXML
    private Label genderField;

    @FXML
    private Label heightField;

    @FXML
    private Label mobileField;

    @FXML
    private Label nameField;

    @FXML
    private Label weightField;

    public void setPatient(Patient patient) {
        this.patient = patient;
        String img = "/images/user.png";
        if(!patient.getImage().equals("null")){ img = patient.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
        patientPhoto.setImage(image);
        ageField.setText(String.valueOf(patient.getAge()));
        nameField.setText(patient.getName());
        emailField.setText(patient.getEmail());
        genderField.setText(patient.getGender());
        bloodGroupField.setText(patient.getBloodType());
        emergencyContactField.setText(String.valueOf(patient.getEmergencyContact()));
        heightField.setText(String.valueOf(patient.getHeight()));
        mobileField.setText(String.valueOf(patient.getMobile()));
        weightField.setText(String.valueOf(patient.getWeight()));
    }
}
