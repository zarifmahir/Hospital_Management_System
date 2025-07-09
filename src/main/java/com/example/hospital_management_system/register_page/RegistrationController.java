package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {
    public AnchorPane centerRegister;
    public ChoiceBox<String> dropdownR;
    public Button backToLogin;

    private Main main;

    private void loadPage(String page) throws IOException {
        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + "Register.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
}
