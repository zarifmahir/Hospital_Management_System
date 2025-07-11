package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class RegistrationController {
    public AnchorPane centerRegister;
    public ChoiceBox<String> dropdownR;
    public Button backToLogin;
    public Label errorMessage2;
    public TextField username;
    public PasswordField password;
    public PasswordField confirmPassword;
    public Label label2;
    public Label label3;
    public Label label4;
    public Text label1;
    public Line line1;
    public Polyline line2;

    private Main main;

    private String type;
    private PatientRegister patientRegister;



    private void loadPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page+"Register.fxml"));
        Parent root = loader.load();

        if(page.equals("Patient")){
            type = "Patient";
            patientRegister = loader.getController();
            patientRegister.setMain(main);
        }
//        try {
//            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + "Register.fxml")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        setAllVisible();
        centerRegister.getChildren().clear();
        centerRegister.getChildren().add(root);
    }

    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Doctor", "Patient");
        dropdownR.setItems(list);
        dropdownR.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    loadPage(newVal);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        setAllInvisible();
    }

    private void setAllInvisible() {
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        confirmPassword.setVisible(false);
    }

    private void setAllVisible() {
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        line1.setVisible(true);
        line2.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        confirmPassword.setVisible(true);
    }



    public void setMain(Main main) {
        initialize();
        this.main = main;
    }

    public void backToLoginPage(ActionEvent actionEvent) {
        try{
            main.showLoginPage();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void signUp(ActionEvent actionEvent) {
        if(type.equals("Patient")){
            if(patientRegister.checkEmpty() || username.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                errorMessage2.setText("Please fill all the fields");
            }
            else if(!password.getText().equals(confirmPassword.getText())){
                errorMessage2.setText("Passwords do not match");
            }
            else{

                Patient p = patientRegister.buildPatient();
                p.setPassAndId(password.getText(), username.getText());
                writePatient(p);
                try{
                    main.showSuccessPage();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    void writePatient(Patient p){
        try {
            String content = p.getName() + "<" + p.getAge() +"<" +p.getGender() +"<"+ p.getWeight() +"<" +p.getHeight() +"<" +p.getBloodType() +"<" +
                    p.getMobile()+"<" +p.getEmergencyContact()+"<"+p.getEmail()+"<"+p.getDiabetes() +"<"+p.getAsthma() +"<"+p.getHighBp() +"<"+
                    p.getEpilepsy() +"<"+p.getCancer() +"<"+p.getStroke() +"<"+p.getKidney() +"<"+p.getLiver() +"<"+
                    p.getAllergies() +"<"+p.getSurgeries() +"<"+p.getFamilyHistory() +"<"+p.getMedications() +"<"+p.getImage()+"<"+
                    p.getId()+"<"+p.getPass();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/PatientsList.txt", true));
            writer.write(content);
            writer.newLine();
            writer.close();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }
}
