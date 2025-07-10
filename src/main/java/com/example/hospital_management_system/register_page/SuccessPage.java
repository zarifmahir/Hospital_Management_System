package com.example.hospital_management_system.register_page;

import com.example.hospital_management_system.Main;
import javafx.event.ActionEvent;

public class SuccessPage {

    private Main main;

    public void setMain(Main main){
        this.main = main;
    }

    public void backToLogIn(ActionEvent actionEvent) {
        try{
            main.showLoginPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
