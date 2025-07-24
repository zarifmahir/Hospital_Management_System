package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.AppointmentMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ShowAppointmentsPage {
    public Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
        List<Appointment> patientAppointments = Main.appointmentMap.getPatientAppointments(patient.getName());
        tableView.getItems().addAll(patientAppointments);

    }

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<Appointment, String> dateCol;

    @FXML
    private TableColumn<Appointment, String> departmentCol;

    @FXML
    private TableColumn<Appointment, String> doctorCol;

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
        doctorCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctorName"));
        idCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("id"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("roomNo"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("department"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
    }

    //SORT APPOINTMENTS BY FOR THAT PATIENT ONLY AND RETURN THAT USE THAT

    @FXML
    void cancelSelectedAppointment(ActionEvent event) {

    }
}
