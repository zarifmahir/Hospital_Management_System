package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.doctor_page.ShowPrescriptionPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientHistoryController {
    public AnchorPane prescriptionPane;
    public AnchorPane testReportPane;
    public TableView<Prescription> prescriptionTable1;
    public TableColumn<Map.Entry<String, String>, String> tIdCol;
    public Button toggleButton;
    public TableView<Map.Entry<String, String>> testReportTable;
    public TableColumn<Map.Entry<String, String>, String> testReportCol;
    public Button testDownloadButton;
    public Button prescriptionButton;

    // private boolean status = true; // true for prescription, false for test report
    private Patient patient;
    public void setPatient(Patient patient) {
        List<Prescription> prescriptionList = Main.prescriptionMap.getPatientPrescriptionList(patient.getId());
        ObservableList<Prescription> observablePrescriptions = FXCollections.observableArrayList(prescriptionList);

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        doctorNameCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        doctorIdCol.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        pIdCol.setCellValueFactory(new PropertyValueFactory<>("myId"));

        prescriptionTable.setItems(observablePrescriptions);
        this.patient = patient;
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
        if (toggleButton.getText().equals("Show Test Reports") && prescriptionTable.getSelectionModel().getSelectedItem() != null) {
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



    void showTestReport() throws IOException {
        if (!toggleButton.getText().equals("Show Test Reports") && testReportTable.getSelectionModel().getSelectedItem() != null) {
            Map.Entry<String, String > entry = testReportTable.getSelectionModel().getSelectedItem();

            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                Path resourcesDir = Paths.get("src/main/resources/pdfs");

                String fileName = entry.getValue();
                Path source = resourcesDir.resolve(fileName);
                Files.copy(source, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            testReportTable.getSelectionModel().clearSelection();
        }
    }

    void loadTestReport() {


            Map<String, String> mp = new HashMap<>();
            for(int i=0; i<patient.getTestReportNumbers(); i++){
                mp.put(String.valueOf(i+1), patient.getId()+"_"+String.valueOf(i+1)+ ".pdf");
            }
            ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(mp.entrySet());
            testReportTable.setItems(items);
            testReportTable.getColumns().clear();

            tIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
            testReportCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

            testReportTable.getColumns().addAll(tIdCol, testReportCol);

//            Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
//            });

    }

    public void toggle(ActionEvent actionEvent) {
        if(toggleButton.getText().equals("Show Test Reports")){
            toggleButton.setText("Show Prescription");
            prescriptionPane.setVisible(false);
            prescriptionButton.setDisable(true);
            testReportPane.setVisible(true);
            testDownloadButton.setDisable(false);
            loadTestReport();
        }
        else{
            toggleButton.setText("Show Test Reports");
            prescriptionPane.setVisible(true);
            prescriptionButton.setDisable(false);
            testReportPane.setVisible(false);
            testDownloadButton.setDisable(true);
            setPatient(patient);
        }

    }

    public void downloadTestReport(ActionEvent actionEvent) throws IOException {
        showTestReport();
    }
}
