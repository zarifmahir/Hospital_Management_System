package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
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
import java.util.List;
import java.util.ResourceBundle;

public class BookAppointmentsPage implements Initializable {

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
    private ChoiceBox<String> timeDropdown;

    @FXML
    private Label unfilledLabel;

    @FXML
    void confirmButtonAction(ActionEvent event) {

    }

    @FXML
    void timeSlotButtonAction(ActionEvent event) {

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
            String selectedDepartment = departmentDropdown.getValue();
            if (selectedDepartment != null) {
                System.out.println("placeholder");
                //load the timeList
            }
            else {
                System.out.println("No Department Selected");
            }
        });



        ObservableList<String> timeList;
    }
}
