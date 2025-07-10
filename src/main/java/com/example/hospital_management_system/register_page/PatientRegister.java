package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;

public class PatientRegister {
    @FXML
    public Label errorMessage;
    @FXML
    public TextField weight;

    @FXML
    public TextField height;

    @FXML
    private CheckBox allergies;

    @FXML
    private CheckBox asthma;

    @FXML
    private TextField bloodGroup;

    @FXML
    private CheckBox cancer;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private CheckBox diabetes;

    @FXML
    private TextField email;

    @FXML
    private TextField emergencyContact;

    @FXML
    private CheckBox epilepsy;

    @FXML
    private CheckBox familyHistory;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private CheckBox highBP;

    @FXML
    private CheckBox kidneyDisease;

    @FXML
    private CheckBox liverDisease;

    @FXML
    private RadioButton male;

    @FXML
    private CheckBox medications;

    @FXML
    private TextField mobile;

    @FXML
    private TextField nameOfAllergies;

    @FXML
    private TextField nameOfFamilyHistory;

    @FXML
    private TextField nameOfMedications;

    @FXML
    private TextField nameOfSurgeries;

    @FXML
    private RadioButton others;

    @FXML
    private TextField patientName;

    @FXML
    private ImageView photo;

    @FXML
    private CheckBox stroke;

    @FXML
    private CheckBox surgeries;

    private Main main;

    private RadioButton selected;




    public void setMain(Main main) {
        initialize();
        this.main = main;
    }


    @FXML
    private void initialize() { //to select only one gender
        // Optionally listen to selection changes
            dateOfBirth.getEditor().setDisable(true);
            dateOfBirth.getEditor().setOpacity(1);
        gender.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                selected = (RadioButton) newToggle;
            }

        });
    }


    public boolean checkEmpty(){
            if(patientName.getText().isEmpty() || email.getText().isEmpty() || bloodGroup.getText().isEmpty() || mobile.getText().isEmpty() || dateOfBirth.getValue()==null || emergencyContact.getText().isEmpty() || gender.getSelectedToggle() == null){
                return true;
            }
            return false;
        }

    


    @FXML
    private void uploadPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter (optional)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Load the image
                Image image = new Image(file.toURI().toString());

                // Set it to the ImageView
                photo.setImage(image);
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

    Patient buildPatient(){
        LocalDate birthDate = dateOfBirth.getValue();
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);

        Patient p = new Patient(patientName.getText(), age.getYears(),  selected.getText(), Float.parseFloat(weight.getText()), Float.parseFloat(height.getText()), bloodGroup.getText(),
                Integer.parseInt(mobile.getText()), Integer.parseInt(emergencyContact.getText()), email.getText(), medications, familyHistory, surgeries, allergies, liverDisease, kidneyDisease, stroke, cancer, epilepsy, highBP, asthma, diabetes);
        return p;
    }

}
