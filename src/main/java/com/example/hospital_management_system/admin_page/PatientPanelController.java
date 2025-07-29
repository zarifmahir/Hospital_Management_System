package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientDashboardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientPanelController implements Initializable {

    public Label selectionLabel;
    public Button editButton;
    public Button viewButton;
    public Button deleteButton;
    @FXML
    private TableColumn<Patient, String> action;

    @FXML
    private TableColumn<Patient, String> dateOfDiagnosis;

    @FXML
    private TableColumn<Patient, String> gender;

    @FXML
    private TableColumn<Patient, String> id;

    @FXML
    private TableColumn<Patient, Integer> mobile;

    @FXML
    private TableColumn<Patient, String> name;

    @FXML
    private TableView<Patient> patientsTable;

    private boolean selectedStatus;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TextField searchBarField;

    ObservableList<Patient> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<Patient, String >("gender"));
        mobile.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("mobile"));

        List<Patient> patientList = Main.patientsMap.getPatientList();
        for(Patient p: patientList){
            patientsTable.getItems().add(p);
            items.add(p);
        }
        patientsTable.setEditable(false);

        Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue!=oldValue){
                    patientsTable.getItems().clear();
                    List<Patient> patientList2 = Main.patientsMap.getPatientList();
                    for(Patient p: patientList2){
                        patientsTable.getItems().add(p);
                        items.add(p);
                    }
                    patientsTable.setEditable(false);
                }
        });

        disableButtons();
        patientsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                enableButtons();
                System.out.println("Hi");
            }
            else{
                disableButtons();
                System.out.println("BYe");
            }
        });

        searchBarField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Patient> filteredItems = FXCollections.observableArrayList();

            if (newValue.trim().isEmpty() || (newValue == null)) {
                patientsTable.setItems(items);
                return;
            }

            for (Patient p : items) {
                if (p.getName().toLowerCase().contains(newValue.toLowerCase())
                || p.getId().toLowerCase().contains(newValue.toLowerCase())) {
                    filteredItems.add(p);
                }
            }

            patientsTable.setItems(filteredItems);
        });
    }

    private void disableButtons(){
        selectedStatus = false;
        selectionLabel.setVisible(true);
        editButton.setDisable(true);
        viewButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
        deleteButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
        viewButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");



    }

    private void enableButtons(){
        patientsTable.setEditable(true);
        selectedStatus = true;
        selectionLabel.setVisible(false);
        editButton.setDisable(false);
        viewButton.setDisable(false);
        deleteButton.setDisable(false);
        editButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        deleteButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        viewButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
    }



    public void edit(ActionEvent actionEvent) {

    }

    public void view(ActionEvent actionEvent) throws IOException {
        if (selectedStatus) {
            Patient patient = patientsTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_popup.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            PatientPopupController controller = fxmlLoader.getController();
            controller.setPatient(patient);

            Stage stage = new Stage();
            stage.setTitle("Patient Information");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }



    public void delete(ActionEvent actionEvent) throws IOException {
        if(selectedStatus) {
            System.out.println("Hello");
            int serial = 0, i=0;
            String deleteName = patientsTable.getSelectionModel().getSelectedItem().getName();
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    String[] s =  line.split("<");
                    if(s[0].equals(deleteName)){serial=i;}
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            System.out.println(serial);
            lines.remove(serial);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/PatientsList.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            patientsTable.getItems().remove(patientsTable.getSelectionModel().getSelectedItem());
            patientsTable.getSelectionModel().clearSelection();
            main.loadPatients();


        }
    }
}
