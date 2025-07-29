package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.doctor_page.ShowPrescriptionPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PatientHistoryController {
    public void setPatient(Patient patient) {
        List<Prescription> prescriptionList = Main.prescriptionMap.getPatientPrescriptionList(patient.getId());
        ObservableList<Prescription> observablePrescriptions = FXCollections.observableArrayList(prescriptionList);

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        doctorNameCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        doctorIdCol.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        pIdCol.setCellValueFactory(new PropertyValueFactory<>("myId"));

        prescriptionTable.setItems(observablePrescriptions);
    }

    @FXML
    private TableColumn<Prescription, String> dateCol;

    @FXML
    private TableColumn<Prescription, String> diagnosisCol;

    @FXML
    private TableColumn<Prescription, String> doctorIdCol;

    @FXML
    private TableColumn<Prescription, String> doctorNameCol;

    @FXML
    private TableColumn<Prescription, String> pIdCol;

    @FXML
    private TableView<Prescription> prescriptionTable;

    @FXML
    void showPrescriptionDetails(ActionEvent event) {
        if (prescriptionTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Prescription prescription = prescriptionTable.getSelectionModel().getSelectedItem();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hospital_management_system/doctor_page/show_prescription_page.fxml"));
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

}
