package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PatientDashboardController {
    @FXML
    public ImageView patientPhoto;

    private Patient patient;

    private PatientPageController patientPageController;

    public void setPatientPageController(PatientPageController patientPageController) {
        this.patientPageController = patientPageController;
    }

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


    @FXML
    private AnchorPane appointmentAp;


    @FXML
    private ListView<String> complicationsListView;

    @FXML
    private Label pastAppointments;

    @FXML
    private AnchorPane prescriptionAp;

    @FXML
    private Label primaryDiagnosisField;

    @FXML
    private Label totalPrescriptions;

    @FXML
    private Label upcomingAppointments;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


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

        List<Appointment> appointmentList = Main.appointmentMap.getPatientAppointments(patient.getId());
        int upcomingAppointmentsCount = 0;
        int previousAppointmentsCount = 0;
        for (Appointment ap : appointmentList) {
            LocalDate date = LocalDate.parse(ap.getDate(), formatter);
            LocalDate today = LocalDate.now();

            if (date.isAfter(today)) {
                upcomingAppointmentsCount++;
            }
            if (date.isBefore(today)) {
                previousAppointmentsCount++;
            }
        }

        upcomingAppointments.setText(String.valueOf(upcomingAppointmentsCount));
        pastAppointments.setText(String.valueOf(previousAppointmentsCount));
        totalPrescriptions.setText(String.valueOf(Main.prescriptionMap.getPatientPrescriptionList(patient.getId()).size()));


        //complicationListView
        if (patient.getCancer())
            complicationsListView.getItems().add("Cancer");
        if (patient.getAsthma())
            complicationsListView.getItems().add("Asthma");
        if (patient.getEpilepsy())
            complicationsListView.getItems().add("Epilepsy");
        if (patient.getStroke())
            complicationsListView.getItems().add("Previous Stroke History");
        if (patient.getLiver())
            complicationsListView.getItems().add("Liver Dysfunction");
        if (patient.getKidney())
            complicationsListView.getItems().add("Kidney Failure");
        if (patient.getDiabetes())
            complicationsListView.getItems().add("Diabetes");
        if (patient.getHighBp())
            complicationsListView.getItems().add("High Bp");




        //primary diagnosis
        if (patient.getPrimaryDiagnosis() != "")
            primaryDiagnosisField.setText(patient.getPrimaryDiagnosis());

    }

    @FXML
    private AnchorPane patientAp;

    @FXML
    void appointmentLabelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), appointmentAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void appointmentLabelReleased(MouseEvent event) throws IOException {
        patientPageController.loadPage("patient_appointments");
    }

    @FXML
    void prescriptionLabelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), prescriptionAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void prescriptionLabelReleased(MouseEvent event) throws IOException {
        patientPageController.loadPage("patient_history");
    }
}
