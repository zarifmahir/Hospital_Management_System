package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.AppointmentMap;
import com.example.hospital_management_system.doctor_page.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookAppointmentsPage implements Initializable {
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private Button confirmButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> departmentDropdown;

    @FXML
    private ChoiceBox<String> doctorDropdown;

    @FXML
    private Button showTimeSlotsButton;

    @FXML
    private Label timeSlotsLabel;

    @FXML
    private ChoiceBox<String> timeDropdown;

    @FXML
    private Label unfilledLabel;


    List<String> timeList = new ArrayList<>(Arrays.asList("4 pm", "5 pm", "6 pm", "7 pm", "8 pm"));
    ObservableList<String> timeListObservable = FXCollections.observableArrayList(timeList);


    @FXML
    void confirmButtonAction(ActionEvent event) {

    }

    @FXML
    void timeSlotButtonAction(ActionEvent event) {
        if (departmentDropdown.getValue() == null || doctorDropdown.getValue() == null || datePicker.getValue() == null) {
            unfilledLabel.setVisible(true);
            timeSlotsLabel.setText("");
            unfilledLabel.setText("Please select all three fields first!");
        }
        else {
            List<String> newTimeList = new ArrayList<>(timeList);

            LocalDate localDate = datePicker.getValue();
            String date = "";

            if (localDate != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = localDate.format(formatter);
            }

            for (String time : timeList) {
                if (Main.appointmentMap.searchAppointment(doctorDropdown.getValue(), date, time)) {
                    newTimeList.remove(time);
                }
            }

            for (String s : newTimeList) {
                System.out.println(s);
            }

            timeListObservable.clear();
            timeListObservable.setAll(newTimeList);
            timeDropdown.setItems(timeListObservable);

            String text = "Available Slots: ";
            if (newTimeList.isEmpty()) {
                text = "No Slots Available";
            }
            else {
                for (String s : newTimeList) {
                    text += s + " ";
                }
            }
            unfilledLabel.setText("");
            timeSlotsLabel.setText(text);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> departmentList = FXCollections.observableArrayList("Medicine", "Surgery", "Pediatrics", "Obstetrics", "Gynecology",
                "Orthopedics", "Cardiology", "Neurology", "Pathology", "Psychiatry", "Dermatology");
        departmentDropdown.setItems(departmentList);

        ObservableList<String> doctorList = FXCollections.observableArrayList();

        departmentDropdown.setOnAction(event -> {
            String selectedDepartment = departmentDropdown.getValue();
            if (selectedDepartment != null) {
                List<Doctor> tempDoctorList = Main.doctorsMap.getDepartmentWiseDoctors(selectedDepartment);
                doctorList.clear();
                for (Doctor doctor : tempDoctorList) {
                    doctorList.add(doctor.getName());
                }

                doctorDropdown.setItems(doctorList);
            }
        });

        doctorDropdown.setOnAction(event -> {
            if (departmentDropdown.getValue() == null) {
                unfilledLabel.setText("Select Department First!");
            }
        });

    }

    public Appointment buildAppointment() {
        Random random = new Random();
        int randomId = 1000 + random.nextInt(9000);

        LocalDate localDate = datePicker.getValue();
        String date = "";

        if (localDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = localDate.format(formatter);
        }

        Appointment appointment = new Appointment(doctorDropdown.getValue(), patient.getName(), date, randomId, timeDropdown.getValue());

        return appointment;
    }
}
