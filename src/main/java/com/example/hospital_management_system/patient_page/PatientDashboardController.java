package com.example.hospital_management_system.patient_page;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PatientDashboardController {
    public ImageView patientPhoto;
    private Patient patient;

    void setPatient(Patient patient) {
        this.patient = patient;
        String img = "/images/user.png";
        if(!patient.getImage().equals("null")){ img = patient.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
        patientPhoto.setImage(image);
    }
}
