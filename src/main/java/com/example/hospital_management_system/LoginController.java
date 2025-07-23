package com.example.hospital_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public ChoiceBox<String> dropdown;
    public Button signUpButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button LogInButton;

    @FXML
    private Label errorMessage;

    private Main main;



    private void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Doctor", "Patient", "Admin");
        dropdown.setItems(list);
    }

    public void userLogIn(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {


        String personType = dropdown.getValue();

        if(dropdown.getValue()== null || username.getText().isEmpty() || password.getText().isEmpty()){
            errorMessage.setVisible(true);
            errorMessage.setText("Please fill all the fields");
        }
        else {
            try{
                if(personType.equals("Doctor")) {
                    String userAndPass = username.getText() + "@" + password.getText();
                    System.out.println(userAndPass);
                    if(Main.doctorsMap.searchDoctor(userAndPass)){
                        main.showDoctorPage(Main.doctorsMap.getDoctor(userAndPass));
                    }
                    else{
                        errorMessage.setText("Invalid username or password");
                    }
                }
                else if (personType.equals("Patient")) {
                    String userAndPass = username.getText() + "@" + password.getText();
                    System.out.println(userAndPass);
                    if(Main.patientsMap.searchPatient(userAndPass)){
                        main.showPatientPage(Main.patientsMap.getPatient(userAndPass));
                    }
                    else{
                        errorMessage.setText("Invalid username or password");
                    }
                }
                else if (personType.equals("Admin")) {
                        String userAndPass = username.getText() + "@" + password.getText();
                        if (Main.adminMap.searchAdmin(userAndPass)){
                            main.showAdminPage(Main.adminMap.getAdmin(userAndPass));
                        }
                        else {
                            errorMessage.setText("Invalid username or password");
                        }
                    }
                }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setMain(Main main) {
        initialize();
        this.main = main;
    }

    public void signUp(ActionEvent actionEvent) {
        try {
            main.showRegisterPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
