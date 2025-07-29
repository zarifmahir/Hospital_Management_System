package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.appointment_system.Prescription;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShowPrescriptionPageController {
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
    private Label doctorNameIdField;

    private Prescription prescription;

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;

        dateField.setEditable(false);
        diagnosisField.setEditable(false);
        remedyField1.setEditable(false);
        remedyField2.setEditable(false);
        remedyField3.setEditable(false);
        remedyField4.setEditable(false);
        remedyField5.setEditable(false);
        remedyField6.setEditable(false);

        dateField.setMouseTransparent(true);
        diagnosisField.setMouseTransparent(true);
        remedyField1.setMouseTransparent(true);
        remedyField2.setMouseTransparent(true);
        remedyField3.setMouseTransparent(true);
        remedyField4.setMouseTransparent(true);
        remedyField5.setMouseTransparent(true);
        remedyField6.setMouseTransparent(true);

        dateField.setFocusTraversable(false);
        diagnosisField.setFocusTraversable(false);
        remedyField1.setFocusTraversable(false);
        remedyField2.setFocusTraversable(false);
        remedyField3.setFocusTraversable(false);
        remedyField4.setFocusTraversable(false);
        remedyField5.setFocusTraversable(false);
        remedyField6.setFocusTraversable(false);


        dateField.setText(String.valueOf(prescription.getDate()));
        diagnosisField.setText(String.valueOf(prescription.getDiagnosis()));
        if (remedyField1 != null) remedyField1.setText(String.valueOf(prescription.getRemedy1()));
        if (remedyField2 != null) remedyField2.setText(String.valueOf(prescription.getRemedy2()));
        if (remedyField3 != null) remedyField3.setText(String.valueOf(prescription.getRemedy3()));
        if (remedyField4 != null) remedyField4.setText(String.valueOf(prescription.getRemedy4()));
        if (remedyField5 != null) remedyField5.setText(String.valueOf(prescription.getRemedy5()));
        if (remedyField6 != null) remedyField6.setText(String.valueOf(prescription.getRemedy6()));
        doctorNameIdField.setText(String.valueOf(prescription.getDoctorName() + " (" + prescription.getDoctorId()) + ")");
    }

}
