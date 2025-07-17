package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.doctor_page.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.print.Doc;
import java.util.Objects;

public class DoctorPopupController {
    @FXML
    private AnchorPane ap;

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
    private Label institutionField;

    @FXML
    private Label medicalDegreeField;

    @FXML
    private Label medicalLicenseField;

    @FXML
    private Label mobileField;

    @FXML
    private Label nameField;

    @FXML
    private ImageView doctorPhoto;

    @FXML
    private Label specializationField;

    @FXML
    private Label yearsActiveField;

    public Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        showDoctor();

    }

    public void showDoctor() {
        String img = "/images/user.png";
        if(!doctor.getImage().equals("null")){ img = doctor.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
        doctorPhoto.setImage(image);
        nameField.setText(doctor.getName());
        ageField.setText(String.valueOf(doctor.getAge()));
        bloodGroupField.setText(String.valueOf(doctor.getBloodGroup()));
        emailField.setText(doctor.getEmail());
        emergencyContactField.setText(doctor.getEmergencyContact());
        genderField.setText(doctor.getGender());
        institutionField.setText(doctor.getInstitution());
        medicalDegreeField.setText(doctor.getMedicalDegree());
        medicalLicenseField.setText(String.valueOf(doctor.getMedicalLicense()));
        mobileField.setText(String.valueOf(doctor.getMobile()));
        specializationField.setText(doctor.getDepartment());
        yearsActiveField.setText(String.valueOf(doctor.getYearsExperience()));
    }

}
