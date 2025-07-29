package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.print.Doc;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowDoctorPanel implements Initializable {
    @FXML
    private ChoiceBox<String> departmentDropdown;

    public TableView<Doctor> doctorsTable;

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

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TableColumn<Doctor, String> status;

    @FXML
    private TextField searchDoctorField;

    ObservableList<Doctor> items = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Medicine", "Surgery", "Pediatrics", "Obstetrics", "Gynecology",
                "Orthopedics", "Cardiology", "Neurology", "Pathology", "Psychiatry", "Dermatology");
        departmentDropdown.setItems(list);


        ID.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        department.setCellValueFactory(new PropertyValueFactory<Doctor, String>("department"));
        phone.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("mobile"));
        room.setCellValueFactory(new PropertyValueFactory<Doctor, String>("room"));
        shift.setCellValueFactory(new PropertyValueFactory<Doctor, String>("shift"));
//        specialization.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));
        status.setCellValueFactory(new PropertyValueFactory<Doctor, String>("status"));

        List<Doctor> doctorList = Main.doctorsMap.getDoctorList();

        departmentDropdown.setOnAction(event -> {
            String selectedDepartment = departmentDropdown.getValue();
            if (selectedDepartment != null) {
                List<Doctor> departmentWiseDoctors = Main.doctorsMap.getDepartmentWiseDoctors(selectedDepartment);
                doctorsTable.getItems().clear();
                doctorsTable.getItems().addAll(departmentWiseDoctors);
            }
        });

        for(Doctor doctor:doctorList){
            doctorsTable.getItems().add(doctor);
            items.add(doctor);
        }

        searchDoctorField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Doctor> filteredDoctors = FXCollections.observableArrayList();

            if ((newValue != null) && (newValue.trim().isEmpty())) {
                doctorsTable.setItems(items);
                return;
            }

            for (Doctor doctor : items) {
                if (doctor.getName().toLowerCase().contains(newValue.toLowerCase())
                || doctor.getId().toLowerCase().contains(newValue.toLowerCase())) {
                    filteredDoctors.add(doctor);
                }
            }

            doctorsTable.setItems(filteredDoctors);
        });
    }


}
