package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AppointmentPageController {

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

    @FXML
    private TableColumn<Appointment, String> patientCol;

    public void initialize() {
        List<Appointment> appointments = Main.appointmentMap.getAppointments();
        tableView.getItems().addAll(appointments);

        dateCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("date"));
        doctorCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctorName"));
        idCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("id"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("roomNo"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("department"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("time"));
        patientCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientName"));
    }

}
