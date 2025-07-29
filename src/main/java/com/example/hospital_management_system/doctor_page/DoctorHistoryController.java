package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorHistoryController {

    private Doctor doctor;

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

    ObservableList<Patient> items = FXCollections.observableArrayList();

    public void loadData() {
        idCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
        patientCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("primaryDiagnosis"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("mobile"));

        List<Patient> patientList = getPatientsUnderDoctor();
        patientList = new ArrayList<>(new HashSet<>(patientList)); //removing duplicates from patientList

        for (Patient patient : patientList) {
            tableView.getItems().add(patient);
            items.add(patient);
        }
    }

    public void initialize() {
        searchPatientField.textProperty().addListener((observable, oldValue, newValue) -> {
           ObservableList<Patient> filteredPatients = FXCollections.observableArrayList();

           if (searchPatientField.getText().isEmpty() || (searchPatientField == null)) {
               tableView.setItems(items);
               return;
           }

           for (Patient patient : items) {
               if (patient.getName().toLowerCase().contains(newValue.toLowerCase())
               || patient.getId().toLowerCase().contains(newValue.toLowerCase())) {
                   filteredPatients.add(patient);
               }
           }

           tableView.setItems(filteredPatients);
        });
    }

    private List<Patient> getPatientsUnderDoctor() {
        List<Patient> patientList = new ArrayList<>();

        List<Appointment> doctorAppointmentList= Main.appointmentMap.getDoctorAppointments(doctor.getName() + " (" + doctor.getId() + ")");

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
    void addSelectedPatientPrescription(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            showErrorWindow();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_new_prescription.fxml"));
            Parent root = fxmlLoader.load();

            AddNewPrescriptionController addNewPrescriptionController = fxmlLoader.getController();
            addNewPrescriptionController.setDoctor(doctor);
            addNewPrescriptionController.setPatient(tableView.getSelectionModel().getSelectedItem());

            Stage stage = new Stage();
            stage.setTitle("Add New Prescription");

            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void showSelectedPatientInformation(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            showErrorWindow();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("show_patient_information.fxml"));
            Parent root = fxmlLoader.load();

            ShowPatientInformationController showPatientInformationController = fxmlLoader.getController();
            showPatientInformationController.setPatient(tableView.getSelectionModel().getSelectedItem());

            Stage stage = new Stage();
            stage.setTitle("Showing Patient Information");

            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void showErrorWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Please select a patient");
        alert.showAndWait();
    }

}
