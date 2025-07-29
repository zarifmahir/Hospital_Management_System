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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorScheduleController {
    public Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        List<Appointment> doctorAppointments = Main.appointmentMap.getDoctorAppointments(doctor.getName() + " (" + doctor.getId() + ")");
        tableView.getItems().addAll(doctorAppointments);
        Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
            tableView.getItems().clear();
            tableView.getItems().addAll(Main.appointmentMap.getDoctorAppointments(doctor.getName() + " (" + doctor.getId() + ")"));
        });

    }

    private Main main;
    public void setMain(Main main) {
        this.main = main;
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
    void cancelSelectedAppointment(ActionEvent event) throws IOException {
        Appointment appointment = tableView.getSelectionModel().getSelectedItem();
        String tobeDeleted = appointment.getDoctorName()+"@"+appointment.getDate()+"@"+appointment.getTime();
        int serial = 0, i=0;
        List<String>lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/AppointmentList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                String[] s =  line.split("<");
                String temp = s[0]+"@"+s[1]+"@"+s[2];
                if(temp.equals(tobeDeleted)) {
                    serial=i;
                    synchronized (Main.c){
                        Main.c.sendMessage("AppointmentList$Remove$"+serial);
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        lines.remove(serial);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/AppointmentList.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        tableView.getSelectionModel().clearSelection();
        main.loadAppointments();
        tableView.getSelectionModel().clearSelection();

    }
}
