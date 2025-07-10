package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class PatientRegister {
    @FXML
    public Label errorMessage;

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

    public void setMain(Main main) {
        initialize();
        this.main = main;
    }


    @FXML
    private void initialize() { //to select only one gender
        // Optionally listen to selection changes
        gender.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
            }

        });
    }


        boolean checkEmpty(){
            if(patientName.getText().isEmpty() || email.getText().isEmpty() || bloodGroup.getText().isEmpty() || mobile.getText().isEmpty() || dateOfBirth.getValue()==null || emergencyContact.getText().isEmpty() || gender.getSelectedToggle() == null){
                return true;
            }
            return false;
        }

    @FXML
    void signUp(ActionEvent event) {
        if(checkEmpty()){
            errorMessage.setText("Please fill all the fields");
        }
        else{
            try {

                main.showSuccessPage();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    @FXML
    void uploadPhoto(ActionEvent event) {

    }

}
