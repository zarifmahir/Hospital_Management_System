package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddNewPrescriptionController {
    public Patient patient;
    public Doctor doctor;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @FXML
    private Button confirmButton;

    @FXML
    private TextField dateField;

    @FXML
    private TextField diagnosisField;

    @FXML
    private TextField remedyField1;

    @FXML
    private TextField remedyField2;

    @FXML
    private TextField remedyField3;

    @FXML
    private TextField remedyField4;

    @FXML
    private TextField remedyField5;

    @FXML
    private TextField remedyField6;

    @FXML
    private Label unfilledLabelField;

    public void initialize() {
        unfilledLabelField.setVisible(false);
        dateField.setEditable(false);
        dateField.setFocusTraversable(false);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);

        dateField.setPromptText(formattedDate);


    }

    @FXML
    void confirm(ActionEvent event) {
        //CLOSE CURRENT PAGE AND OPEN NEW PAGE
        //BUILD PRESCRIPTION
        //PUT PRESCRIPTION IN PRESCRIPTIONMAP
        //WRITE IT TO PRESCRIPTIONS.TXT

        if (diagnosisField.getText().isEmpty() || (remedyField1.getText().isEmpty() && remedyField2.getText().isEmpty() && remedyField3.getText().isEmpty() && remedyField4.getText().isEmpty() && remedyField5.getText().isEmpty() && remedyField6.getText().isEmpty())) {
            unfilledLabelField.setVisible(true);
        }
        else {
            unfilledLabelField.setVisible(false);
            buildPrescription();

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        }
    }

    private void buildPrescription() {
        String s = diagnosisField.getText() + "|" + doctor.getName() + "|" + doctor.getId() + "|" + patient.getName() + "|" + patient.getId() + "|" + dateField.getPromptText();

        System.out.println(s);

        Prescription prescription = new Prescription(diagnosisField.getText(), doctor.getName(), doctor.getId(), patient.getName(), patient.getId(), dateField.getPromptText());

        if (!remedyField1.getText().isEmpty()) {
            s += "|" + remedyField1.getText();
            prescription.setRemedy1(remedyField1.getText());
        }
        if (!remedyField2.getText().isEmpty()) {
            s += "|" + remedyField2.getText();
            prescription.setRemedy2(remedyField2.getText());
        }
        if (!remedyField3.getText().isEmpty()) {
            s += "|" + remedyField3.getText();
            prescription.setRemedy3(remedyField3.getText());
        }
        if (!remedyField4.getText().isEmpty()) {
            s += "|" + remedyField4.getText();
            prescription.setRemedy4(remedyField4.getText());
        }
        if (!remedyField5.getText().isEmpty()) {
            s += "|" + remedyField5.getText();
            prescription.setRemedy5(remedyField5.getText());
        }
        if (!remedyField6.getText().isEmpty()) {
            s += "|" + remedyField6.getText();
            prescription.setRemedy6(remedyField6.getText());
        }

        System.out.println(s);

        Main.prescriptionMap.addPrescription(prescription);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/texts/PrescriptionList.txt", true))) {
            System.out.println("File writter opened");
            bw.write(s);
            System.out.println("Wrote new prescription");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
