package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientDashboardController;
import com.example.hospital_management_system.patient_page.PatientPageController;
import com.example.hospital_management_system.register_page.RegistrationController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientPanelController implements Initializable {

    public Label selectionLabel;
    public Button editButton;
    public Button viewButton;
    public Button deleteButton;
    public TextField editId;
    public TextField editName;
    public TextField editMobile;
    public ImageView newImage;
    public TextField editWeight;
    public TextField editHeight;
    public TextField editEmCon;
    public TextField editEmail;
    public TextField editBloodGroup;
    public AnchorPane editPane;
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

    private boolean isEdited = false;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<Patient, String >("gender"));
        mobile.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("mobile"));

        List<Patient> patientList = Main.patientsMap.getPatientList();
        for(Patient p: patientList){
            patientsTable.getItems().add(p);
        }
        patientsTable.setEditable(false);

        Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue!=oldValue){
                    patientsTable.getItems().clear();
                    List<Patient> patientList2 = Main.patientsMap.getPatientList();
                    for(Patient p: patientList2){
                        patientsTable.getItems().add(p);
                    }
                    patientsTable.setEditable(false);
                }
        });

        disableButtons();
        editPane.setVisible(false);
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
        if(selectedStatus){
            editPane.setVisible(true);
            Patient patient = patientsTable.getSelectionModel().getSelectedItem();
            editName.setPromptText(patient.getName());
            editId.setPromptText(patient.getId());
            editMobile.setPromptText(String.valueOf(patient.getMobile()));
            editEmail.setPromptText(patient.getEmail());
            editBloodGroup.setPromptText(patient.getBloodType());
            editEmCon.setPromptText(String.valueOf(patient.getEmergencyContact()));
            editWeight.setPromptText(String.valueOf(patient.getWeight()));
            editHeight.setPromptText(String.valueOf(patient.getHeight()));
            String img = "/images/user.png";
            if(!patient.getImage().equals("null")){ img = patient.getImage();}
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
            newImage.setImage(image);
        }

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
        if(selectedStatus && showDeleteAlert()) {
            System.out.println("Hello");
            int serial = 0, i=0;
            String deleteName = patientsTable.getSelectionModel().getSelectedItem().getId();
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    String[] s =  line.split("\\|");
                    if(s[0].equals(deleteName)){
                        serial=i;
                        synchronized (Main.c){
                            Main.c.sendMessage("PatientsList$Remove$"+serial);
                        }
                    }
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
            selectedStatus = false;

        }
    }

    public boolean showDeleteAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deletion Confirmation");
        alert.setHeaderText("Are you sure you want to delete this person?");
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType2 = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType, buttonType2);
        alert.showAndWait();
        return alert.getResult() != buttonType2;

    }
    public boolean showEditAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Edit Confirmation");
        alert.setHeaderText("Are you sure you want to edit the data?");
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType2 = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType, buttonType2);
        alert.showAndWait();
        return alert.getResult() != buttonType2;

    }

    public void showSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success Edit");
        alert.show();
    }

    public void editImage(ActionEvent actionEvent) {
        Patient patient = patientsTable.getSelectionModel().getSelectedItem();
        String img = "/images/user.png";

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Image image = new Image(file.toURI().toString());
                //System.out.println(file.toURI().toString());
                img = "/images/"+file.getName();
                patient.setImage(img);

                // Set it to the ImageView
                newImage.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel(ActionEvent actionEvent) {
        if(selectedStatus){
            editPane.setVisible(false);
            patientsTable.getSelectionModel().clearSelection();
        }
    }

    public void apply(ActionEvent actionEvent) throws IOException {
        if(selectedStatus && showEditAlert()){
            Patient patient = patientsTable.getSelectionModel().getSelectedItem();
            String prevId = patient.getId();
            if(!editId.getText().isEmpty())  patient.setId(editId.getText());
            if(!editName.getText().isEmpty()) patient.setName(editName.getText());
            if(!editMobile.getText().isEmpty()) patient.setMobile(Integer.parseInt(editMobile.getText()));
            if(!editEmail.getText().isEmpty()) patient.setEmail(editEmail.getText());
            if(!editEmCon.getText().isEmpty()) patient.setEmergencyContact(Integer.parseInt(editEmCon.getText()));
            if(!editWeight.getText().isEmpty()) patient.setWeight(Float.parseFloat(editWeight.getText()));
            if(!editHeight.getText().isEmpty()) patient.setHeight(Float.parseFloat(editHeight.getText()));
            if(!editBloodGroup.getText().isEmpty()) patient.setBloodType(editBloodGroup.getText());

            int serial = 0;
            int i=0;
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/PatientsList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    lines.add(line);
                    String[] s =  line.split("\\|");
                    if(s[0].equals(prevId)){
                        serial=i;
                        synchronized (Main.c){
                            Main.c.sendMessage("PatientsList$Remove$"+serial);
                        }
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            System.out.println(lines.size());
            System.out.println(serial);

            lines.remove(serial);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/PatientsList.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            RegistrationController.writePatient(patient);
            patientsTable.getItems().remove(patientsTable.getSelectionModel().getSelectedItem());
            main.loadDoctors();
            patientsTable.getItems().add(Main.patientsMap.getPatientById(patient.getId()));
            isEdited = true;
        }
    }

    public void ok(ActionEvent actionEvent) {
        if(selectedStatus){
            if(isEdited) showSuccessAlert();
            editPane.setVisible(false);
            patientsTable.getSelectionModel().clearSelection();
        }
    }
}
