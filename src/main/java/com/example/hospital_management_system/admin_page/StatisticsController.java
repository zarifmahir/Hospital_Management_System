package com.example.hospital_management_system.admin_page;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    private AreaChart<String, Number> doctorsChart;

    @FXML
    private AreaChart<String, Number> patientsChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
