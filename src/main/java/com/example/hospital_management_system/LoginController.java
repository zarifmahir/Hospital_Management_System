package com.example.hospital_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {

    @FXML
    public ChoiceBox<String> dropdown;

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

        String validUsername = "zarif";
        String validPassword = "123";
        String personType = dropdown.getValue();
        if(dropdown.getValue()== null || username.getText().isEmpty() || password.getText().isEmpty()){
            errorMessage.setVisible(true);
            errorMessage.setText("Please fill all the fields");
        }
        else if(username.getText().equals(validUsername) && password.getText().equals(validPassword)){
            try{
                if(personType.equals("Doctor")) {
                    main.showHomePage();
                }
                else if (personType.equals("Patient")) {
                    main.showPatientPage();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            //errorMessage.setVisible(true);
            errorMessage.setText("Username or password is incorrect");
        }
    }

    public void setMain(Main main) {
        initialize();
        this.main = main;
    }
}
