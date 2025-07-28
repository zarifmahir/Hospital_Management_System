package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public Button buttonForSignUp;

    private Main main;

    private String type;
    private PatientRegister patientRegister;
    private DoctorRegister doctorRegister;



    private void loadPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page+"Register.fxml"));
        Parent root = loader.load();

        if(page.equals("Patient")){
            type = "Patient";
            patientRegister = loader.getController();
            patientRegister.setMain(main);
        }
        else if(page.equals("Doctor")){
            type = "Doctor";
            doctorRegister = loader.getController();
            doctorRegister.setMain(main);
            buttonForSignUp.setText("Apply");
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
        dropdownR.setOnAction(event -> {
            if (dropdownR.getValue() != null) {
                try {
                    loadPage(dropdownR.getValue());
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
        buttonForSignUp.setVisible(false);
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
        buttonForSignUp.setVisible(true);
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

    public void signUp(ActionEvent actionEvent) throws IOException {
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
                    patientRegister.updateNumber();
                    main.showSuccessPage("Successfully registered!!!");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if(type.equals("Doctor")){
            if(doctorRegister.checkEmpty() || username.getText().isEmpty()|| password.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                errorMessage2.setText("Please fill all the fields");
            }
            else if(!password.getText().equals(confirmPassword.getText())){
                errorMessage2.setText("Passwords do not match");
            }
            else{
                Doctor doctor = doctorRegister.buildDoctor();
                doctorRegister.updateNumber();
                doctor.setUserNameAndPass(username.getText(), password.getText());
                writeDoctor(doctor);
                try{

                    main.showSuccessPage("Applied Successfully!!!!");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    void writePatient(Patient p){
        try {
            String content = p.getId() + "|" + p.getName() + "|" + p.getAge() +"|" +p.getGender() +"|"+ p.getWeight() +"|" +p.getHeight() +"|" +p.getBloodType() +"|" +
                    p.getMobile()+"|" +p.getEmergencyContact()+"|"+p.getEmail()+"|"+p.getDiabetes() +"|"+p.getAsthma() +"|"+p.getHighBp() +"|"+
                    p.getEpilepsy() +"|"+p.getCancer() +"|"+p.getStroke() +"|"+p.getKidney() +"|"+p.getLiver() +"|"+
                    p.getAllergies() +"|"+p.getSurgeries() +"|"+p.getFamilyHistory() +"|"+p.getMedications() +"|"+p.getImage()+"|"+
                    p.getUsername()+"|"+p.getPass();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/PatientsList.txt", true));
            writer.write(content);
            writer.newLine();
            writer.close();
            synchronized (Main.c.WTC){
                System.out.println("Patient writng");
                Main.c.sendMessage("PatientsList$"+content);
            }

            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    void writeDoctor(Doctor doctor){
        try{
            String content = doctor.getId()+"|"+doctor.getName() + "|" + doctor.getAge() + "|" + doctor.getGender() + "|" + doctor.getBloodGroup() + "|" + doctor.getEmail() + "|"
                    + doctor.getMobile() + "|" + doctor.getEmergencyContact() + "|" + doctor.getMedicalDegree() + "|" + doctor.getInstitution() + "|" + doctor.getPgQualification()
                    + "|" + doctor.getMedicalLicense() + "|" + doctor.getDepartment() + "|" + doctor.getYearsExperience() + "|" + doctor.getMedicalCouncil()+"|"+doctor.getImage()
                    +"|"+doctor.getUserName()+"|"+doctor.getPass();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/DoctorsList.txt", true));
            writer.write(content);

            synchronized (Main.c){
                Main.c.sendMessage("DoctorsList$" + content);
            }
            writer.newLine();
            writer.close();
            System.out.println("File written successfully.");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
