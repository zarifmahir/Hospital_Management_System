package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.AppointmentMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Objects;

import static com.example.hospital_management_system.Main.staffMap;

public class AdminDashboardController {
    @FXML
    private AnchorPane apTop;

    @FXML
    private Label designationField;

    @FXML
    private Label idField;

    @FXML
    private Label nameField;

    @FXML
    private Circle pfpCircle;

    @FXML
    private Label roomField;


    public Admin admin;
    public void setAdmin(Admin admin) {
        this.admin = admin;
        designationField.setText(admin.getDesignation());
        nameField.setText(admin.getName());
        idField.setText(admin.getUser());
        roomField.setText(admin.getRoom_number());

        String img = "/images/user.png";
        if(!admin.getImage().equals("null")){ img = admin.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
//        doctorPhoto.setImage(image);
        pfpCircle.setFill(new ImagePattern(image));


        numAppointmentField.setText(String.valueOf(Main.appointmentMap.size()));
        numDoctorField.setText(String.valueOf(Main.doctorsMap.size()));
        numPatientField.setText(String.valueOf(Main.patientsMap.size()));
//      numReportField.setText(Re)
        numStaffField.setText(String.valueOf(staffMap.size()));
    }


    @FXML
    private Label numAppointmentField;


    @FXML
    private Label numDoctorField;

    @FXML
    private Label numPatientField;

    @FXML
    private Label numReportField;

    @FXML
    private Label numStaffField;


    @FXML
    void appointmentHover(MouseEvent event) {

    }

    @FXML
    void appointmentHoverExited(MouseEvent event) {

    }

    @FXML
    void appointmentPressed(MouseEvent event) {

    }

    @FXML
    void doctorLabelHover(MouseEvent event) {

    }

    @FXML
    void doctorLabelHoverExited(MouseEvent event) {

    }

    @FXML
    void patientHover(MouseEvent event) {

    }

    @FXML
    void patientHoverExited(MouseEvent event) {

    }

    @FXML
    void reportHover(MouseEvent event) {

    }

    @FXML
    void reportHoverExited(MouseEvent event) {

    }

    @FXML
    void reportPressed(MouseEvent event) {

    }

    @FXML
    void showAppointmentPage(MouseEvent event) {

    }

    @FXML
    void showDoctorPanel(MouseEvent event) {

    }

    @FXML
    void showPatientPanel(MouseEvent event) {

    }

    @FXML
    void showReportPage(MouseEvent event) {

    }

    @FXML
    void showStaffPanel(MouseEvent event) {

    }

    @FXML
    void staffHover(MouseEvent event) {

    }

    @FXML
    void staffHoverExited(MouseEvent event) {

    }

    @FXML
    void staffPressed(MouseEvent event) {

    }

}
