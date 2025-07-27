package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;

public class PatientRegister {
    @FXML
    public Label errorMessage;
    @FXML
    public TextField weight;

    @FXML
    public TextField height;
    public TextField pID;

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

    private String img;

    private String updated;

    private int id;


    public void setMain(Main main) throws IOException {
        initialize();
        this.main = main;
        setpID();
    }

    private void setpID() throws IOException {
        pID.setEditable(false);
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/Numbers.txt"));
        String idLine = br.readLine();
        br.close();
        String[] values = idLine.split("\\|");
        System.out.println(values[0]);
        System.out.println("Here");
        id = Integer.parseInt(values[0])+1;
        updated = String.valueOf(id)+"|"+values[1];
        if(id<10) pID.setPromptText("00"+String.valueOf(id));
        else if(id<100) pID.setPromptText("0"+String.valueOf(id));
        else pID.setPromptText(String.valueOf(id));
    }

    public void updateNumber() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/texts/Numbers.txt"));
        bw.write(updated);
        bw.close();
    }


    @FXML
    private void initialize() throws IOException {
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
                //convert to string



                // Load the image
                Image image = new Image(file.toURI().toString());
                //System.out.println(file.toURI().toString());
                img = "/images/"+file.getName();

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
        String idStr = String.valueOf(id);
        if(id<10) idStr = "00"+String.valueOf(id);
        else if(id<100) idStr = "0"+String.valueOf(id);

        Patient p = new Patient(idStr, patientName.getText(), age.getYears(),  selected.getText(), Float.parseFloat(weight.getText()), Float.parseFloat(height.getText()), bloodGroup.getText(),
                Integer.parseInt(mobile.getText()), Integer.parseInt(emergencyContact.getText()), email.getText(), diabetes.isSelected(), asthma.isSelected(), highBP.isSelected(), epilepsy.isSelected(),
                cancer.isSelected(), stroke.isSelected(), kidneyDisease.isSelected(), liverDisease.isSelected(), allergies.isSelected(), surgeries.isSelected(), familyHistory.isSelected(),
                medications.isSelected(), img);
        return p;
    }

}
