package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Period;

public class DoctorRegister {

    public Label errorMessage;
    public ToggleGroup doctorGender;
    public Button certificateUpload;
    public Button degreeUpload;
    @FXML
    private TextField bloodGroup;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField degrees;

    @FXML
    private ImageView doctorImage;

    @FXML
    private TextField doctorName;

    @FXML
    private TextField email;

    @FXML
    private TextField emergencyContact;

    @FXML
    private TextField experience;

    @FXML
    private RadioButton female;

    @FXML
    private TextField institution;

    @FXML
    private TextField licenseNumber;

    @FXML
    private RadioButton male;

    @FXML
    private TextField medicalCouncil;

    @FXML
    private TextField mobile;

    @FXML
    private RadioButton others;

    @FXML
    private TextField pgQualification;

    @FXML
    private ChoiceBox<String> departmentDropdown;

    private Main main;

    private RadioButton selected;

    private String img;




    public void setMain(Main main) {
        initialize();
        this.main = main;
    }

    private String department;


    @FXML
    private void initialize() {
        //to select only one gender
        // Optionally listen to selection changes

        ObservableList<String> list = FXCollections.observableArrayList("Medicine", "Surgery", "Pediatrics", "Obstetrics", "Gynecology",
                "Orthopedics", "Cardiology", "Neurology", "Pathology", "Psychiatry", "Dermatology");
        departmentDropdown.setItems(list);

//        departmentDropdown.valueProperty().addListener((obs, oldVal, newVal) -> {
//            if (newVal != null) {
//                department = newVal;
//                System.out.println("Department selected: " + department);
//            }
//        });

        dateOfBirth.getEditor().setDisable(true);
        dateOfBirth.getEditor().setOpacity(1);
        doctorGender.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                selected = (RadioButton) newToggle;
            }

        });
    }


    public boolean checkEmpty(){
        if(doctorName.getText().isEmpty() || email.getText().isEmpty() || bloodGroup.getText().isEmpty() || mobile.getText().isEmpty() || dateOfBirth.getValue()==null || emergencyContact.getText().isEmpty() || doctorGender.getSelectedToggle() == null || licenseNumber.getText().isEmpty() || (departmentDropdown.getValue() == null)
        || experience.getText().isEmpty()|| degrees.getText().isEmpty() ||  medicalCouncil.getText().isEmpty()){
            return true;
        }
        return false;
    }


   @FXML
    private void uploadPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Image image = new Image(file.toURI().toString());
                //System.out.println(file.toURI().toString());
                img = "/images/"+file.getName();

                // Set it to the ImageView
                doctorImage.setImage(image);
            } catch (Exception e) {
                showAlert("Error", "Could not load image: " + e.getMessage());
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void uploadCertificate(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Path resourcesDir = Paths.get("src/main/resources/pdfs");
            String fileName = doctorName.getText()+ ".pdf";
            Path destination = resourcesDir.resolve(fileName);
            Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            certificateUpload.setText(fileName);
        }

    }

    @FXML
    void uploadDegrees(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Path resourcesDir = Paths.get("src/main/resources/pdfs");
            String fileName = doctorName.getText() + "_degrees.pdf";
            Path destination = resourcesDir.resolve(fileName);
            Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            degreeUpload.setText(fileName);
        }

    }

    Doctor buildDoctor(){
        LocalDate birthDate = dateOfBirth.getValue();
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);

        Doctor doctor = new Doctor(doctorName.getText(), age.getYears(), selected.getText(), bloodGroup.getText(), email.getText(), mobile.getText(),
                emergencyContact.getText(), degrees.getText(), institution.getText(), pgQualification.getText(), Integer.parseInt(licenseNumber.getText()),
                departmentDropdown.getValue(), Integer.parseInt(experience.getText()), medicalCouncil.getText(), img);
        return doctor;
    }




}
