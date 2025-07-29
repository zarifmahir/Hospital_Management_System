package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ShowPatientInformationController {
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;


        ageField.setText(String.valueOf(patient.getAge()));
        nameField.setText(patient.getName());
        genderField.setText(patient.getGender());
        heightField.setText(String.valueOf(patient.getHeight()));
        weightField.setText(String.valueOf(patient.getWeight()));
        primaryDiagnosisField.setText(patient.getPrimaryDiagnosis());
        phoneField.setText(String.valueOf(patient.getMobile()));
        emergencyContactField.setText(String.valueOf(patient.getEmergencyContact()));

        String img = "/images/user.png";
        if(!patient.getImage().equals("null")){ img = patient.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
        pfpImageView.setImage(image);

        ObservableList<String> items = getStrings(patient);
        complicationsListView.setItems(items);



        //load the table view as well
        List<Prescription> prescriptionList = Main.prescriptionMap.getPatientPrescriptionList(patient.getId());


        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        doctorNameCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

        ObservableList<Prescription> observablePrescriptions = FXCollections.observableArrayList(prescriptionList);
        prescriptionTable.setItems(observablePrescriptions);
    }


    @FXML
    private ListView<String> complicationsListView;


    @FXML
    private TableView<Prescription> prescriptionTable;
    @FXML
    private TableColumn<Prescription, String> dateCol;
    @FXML
    private TableColumn<Prescription, String> doctorNameCol;


    @FXML
    private Label ageField;

    @FXML
    private Label emergencyContactField;

    @FXML
    private Label genderField;

    @FXML
    private Label heightField;

    @FXML
    private Label nameField;

    @FXML
    private Label nameField1;

    @FXML
    private ImageView pfpImageView;

    @FXML
    private Label phoneField;


    @FXML
    private Label primaryDiagnosisField;

    @FXML
    private Button showPrescriptionDetailsButton;

    @FXML
    private Label weightField;

    @FXML
    void showSelectedPrescriptionDetails(ActionEvent event) {
        if (prescriptionTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Prescription prescription = prescriptionTable.getSelectionModel().getSelectedItem();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("show_prescription_page.fxml"));
                Parent root = fxmlLoader.load();

                ShowPrescriptionPageController controller = fxmlLoader.getController();
                controller.setPrescription(prescription);

                Stage stage = new Stage();
                stage.setTitle("Showing Prescription Details (ID: " + prescription.getMyId() + ")");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static ObservableList<String> getStrings(Patient patient) {
        ObservableList<String> items = FXCollections.observableArrayList();

        if (patient.getDiabetes()) {
            items.add("Diabetes");
        }
        if (patient.getHighBp()) {
            items.add("High BP");
        }
        if (patient.getStroke()) {
            items.add("Stroke");
        }
        if (patient.getAsthma()) {
            items.add("Asthma");
        }
        if (patient.getEpilepsy()) {
            items.add("Epilepsy");
        }
        if (patient.getKidney()) {
            items.add("Kidney Failure");
        }
        if (patient.getLiver()) {
            items.add("Liver Dysfunction");
        }
        return items;
    }
}
