package com.example.hospital_management_system.patient_page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class PatientAppointmentsController {
    ComboBox<String> comboBox;
    String type;

    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Show my appointments", "Book appointments", "Cancel my appointments");
        comboBox.setItems(list);
        comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    loadPage(newVal);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loadPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page+".fxml"));
        Parent root = loader.load();

        if(page.equals("Show my appointments")){
            type = "Show my appointments";

        }
        else if(page.equals("Book appointments")){
            type = "Book appointments";

        }
        else if (page.equals("Cancel my appointments")){
            type = "Cancel my appointments";

        }

//        centerRegister.getChildren().clear();
//        centerRegister.getChildren().add(root);
    }


}
