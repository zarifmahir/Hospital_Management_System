package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class DoctorScheduleController {
    public Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        List<Appointment> doctorAppointments = Main.appointmentMap.getDoctorAppointments(doctor.getName() + " (" + doctor.getId() + ")");
        tableView.getItems().addAll(doctorAppointments);

    }

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<Appointment, String> dateCol;

    @FXML
    private TableColumn<Appointment, String> departmentCol;

    @FXML
    private TableColumn<Appointment, String> patientCol;

    @FXML
    private TableColumn<Appointment, Integer> idCol;

    @FXML
    private TableColumn<Appointment, Integer> roomCol;

    @FXML
    private TableView<Appointment> tableView;

    @FXML
    private TableColumn<Appointment, String> timeCol;

    public void initialize() {
        dateCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("date"));
        patientCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientName"));
        idCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("id"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("roomNo"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("department"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
    }

    @FXML
    void cancelSelectedAppointment(ActionEvent event) {

    }
}
