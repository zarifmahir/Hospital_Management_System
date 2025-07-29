package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SuccessPage {

    public Label successMessage;
    private Main main;

    public void setMain(Main main){
        this.main = main;
    }

    public void setSuccessMessage(String message){
        successMessage.setText(message);
    }

    public void backToLogIn(ActionEvent event) {
        try{
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            main.showLoginPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
