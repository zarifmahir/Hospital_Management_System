package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DoctorDashboardController {
    DoctorPageController doctorPageController;

    public void setDoctorPageController(DoctorPageController doctorPageController) {
        this.doctorPageController = doctorPageController;
    }

    public Label departmentField;
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
    private Circle pfpCircle;

    @FXML
    private Label yearsActiveField;

    @FXML
    private TableColumn<Appointment, Integer> aptIdCol;

    @FXML
    private TableColumn<Appointment, String> patientIdCol;

    @FXML
    private TableColumn<Appointment, String> patientNameCol;



    @FXML
    private TableView<Appointment> tableView;

    @FXML
    private TableColumn<Appointment, String> timeCol;

    @FXML
    private Label totalPatientCount;

    @FXML
    private Label upcomingAppointmentCount;

    @FXML
    private AnchorPane patientAp;

    @FXML
    private AnchorPane appointmentAp;


    @FXML
    void appointmentLabelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), appointmentAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void appointmentLabelReleased(MouseEvent event) throws IOException {
        doctorPageController.loadPage("doctor_schedule");
    }

    @FXML
    void patientLabelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), patientAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void patientLabelReleased(MouseEvent event) throws IOException {
        doctorPageController.loadPage("doctor_patient_history");
    }


    public void setDoctor(Doctor doctor) {
        String img = "/images/user.png";
        if(!doctor.getImage().equals("null")){ img = doctor.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
//        doctorPhoto.setImage(image);
        pfpCircle.setFill(new ImagePattern(image));
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
        departmentField.setText(doctor.getDepartment());
        yearsActiveField.setText(String.valueOf(doctor.getYearsExperience()));


        //tableview setting
        List<Appointment> appointmentList = Main.appointmentMap.getDoctorAppointments(doctor.getId());
        int upCount = 0;
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now();
        for (Appointment a : appointmentList) {
            LocalDate date = LocalDate.parse(a.getDate());
            if(date.equals(today)){
                appointmentObservableList.add(a);
            }
            if (date.isAfter(today)) {
                upCount++;
            }
        }

        //set cols
        aptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        tableView.setItems(appointmentObservableList);


        //appointmentCount
        upcomingAppointmentCount.setText(String.valueOf(upCount));


        //patientcount
        Map<Doctor, Patient> mp = new HashMap<>();
        List<Prescription> prescriptionList = Main.prescriptionMap.getDoctorPrescriptionList(doctor.getId());

        for (Prescription p : prescriptionList) {
            mp.put(doctor , Main.patientsMap.getPatientById(String.valueOf(p.getPatientId())));
        }

        for (Appointment a : appointmentList) {
            mp.put(doctor, Main.patientsMap.getPatientById(String.valueOf(a.getPatientId())));
        }

        totalPatientCount.setText(String.valueOf(mp.size()));
    }


}
