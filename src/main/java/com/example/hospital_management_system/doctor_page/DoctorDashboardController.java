package com.example.hospital_management_system.doctor_page;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class DoctorDashboardController {

    public ImageView doctorPhoto;
    @FXML
    private AnchorPane ap;
    private Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        String img = "/images/user.png";
        if(!doctor.getImage().equals("null")){ img = doctor.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
        doctorPhoto.setImage(image);
    }
}
