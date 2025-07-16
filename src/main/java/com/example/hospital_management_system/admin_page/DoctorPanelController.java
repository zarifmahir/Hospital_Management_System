package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.doctor_page.DoctorsMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorPanelController implements Initializable {

    public TableView<Doctor> doctorsPanel;
    @FXML
    private TableColumn<Doctor, String> ID;

    @FXML
    private TableColumn<Doctor, String> department;

    @FXML
    private TableColumn<Doctor, String> name;

    @FXML
    private TableColumn<Doctor, Integer> phone;

    @FXML
    private TableColumn<Doctor, String> room;

    @FXML
    private TableColumn<Doctor, String> shift;

    @FXML
    private TableColumn<Doctor, String> specialization;

    @FXML
    private TableColumn<Doctor, String> status;

    @FXML
    void deleteButton(ActionEvent event) {

    }

    @FXML
    void editButton(ActionEvent event) {

    }

    @FXML
    void viewButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        department.setCellValueFactory(new PropertyValueFactory<Doctor, String>("department"));
        phone.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("mobile"));
        room.setCellValueFactory(new PropertyValueFactory<Doctor, String>("room"));
        shift.setCellValueFactory(new PropertyValueFactory<Doctor, String>("shift"));
        specialization.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));
        status.setCellValueFactory(new PropertyValueFactory<Doctor, String>("status"));

        List<Doctor> doctorList = Main.doctorsMap.getDoctorList();
        for(Doctor doctor:doctorList){
            doctorsPanel.getItems().add(doctor);
        }

    }
}
