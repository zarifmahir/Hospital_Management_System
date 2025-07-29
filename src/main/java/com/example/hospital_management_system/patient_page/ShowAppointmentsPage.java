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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShowAppointmentsPage {
    public Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
        List<Appointment> patientAppointments = Main.appointmentMap.getPatientAppointments(patient.getName());
        tableView.getItems().addAll(patientAppointments);

        Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
            tableView.getItems().clear();
            List<Appointment> patientAppointments2 = Main.appointmentMap.getPatientAppointments(patient.getName());
            tableView.getItems().addAll(patientAppointments);
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
                String temp = s[0]+"@"+s[2]+"@"+s[4];
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
