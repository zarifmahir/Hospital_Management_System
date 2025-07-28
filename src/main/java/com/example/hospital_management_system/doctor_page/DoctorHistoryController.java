package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorHistoryController {

    public Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    @FXML
    private TableColumn<Patient, Integer> ageCol;

    @FXML
    private TableColumn<Patient, String> diagnosisCol;

    @FXML
    private TableColumn<Patient, String> genderCol;

    @FXML
    private TableColumn<Patient, String> idCol;

    @FXML
    private TableColumn<Patient, String> patientCol;

    @FXML
    private TableColumn<Patient, Integer> phoneNoCol;

    @FXML
    private TextField searchPatientField;

    @FXML
    private TableView<Patient> tableView;

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
        patientCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("primaryDiagnosis"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("mobile"));

        List<Patient> patientList = getPatientsUnderDoctor();

        for (Patient patient : patientList) {
            tableView.getItems().add(patient);
        }
    }

    private List<Patient> getPatientsUnderDoctor() {
        List<Patient> patientList = new ArrayList<>();

        List<Appointment> doctorAppointmentList = Main.appointmentMap.getDoctorAppointments(doctor.getName() + "(" + doctor.getId() + ")");

        for (Appointment a : doctorAppointmentList) {
            Patient p = Main.patientsMap.getPatientById(a.getPatientId());

            if (p != null) {
                patientList.add(p);
            }
        }

        List<Prescription> doctorPrescriptionList = Main.prescriptionMap.getDoctorPrescriptionList(doctor.getId());

        for (Prescription p : doctorPrescriptionList) {
            Patient patient = Main.patientsMap.getPatientById(p.getPatientId());

            if (patient != null) {
                patientList.add(patient);
            }
        }

        return patientList;
    }


    @FXML
    private Button viewPatientButton;

    @FXML
    private Button addPrescriptionButton;
    @FXML
    void addSelectedPatientPrescription(ActionEvent event) {

    }

    @FXML
    void showSelectedPatientInformation(ActionEvent event) {
        Patient patient = tableView.getSelectionModel().getSelectedItem();

        if (patient == null) {
            //SHOW ERROR SAYING YOU HAVE TO SELECT A PATIENT
        }
        else {
            //CREATE A NEW LOADING FXML FILE SHOWING THE PATIENT INFORMATION
        }
    }

}
