package com.example.hospital_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button LogInButton;

    @FXML
    private Label errorMessage;

    public void userLogIn(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

    }
}
