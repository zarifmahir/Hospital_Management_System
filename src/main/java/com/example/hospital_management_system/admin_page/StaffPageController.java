package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.Staff.BedBoy;
import com.example.hospital_management_system.Staff.Nurse;
import com.example.hospital_management_system.Staff.Receptionist;
import com.example.hospital_management_system.Staff.Staff;
import com.example.hospital_management_system.doctor_page.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.hospital_management_system.Main.staffMap;

public class StaffPageController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane centrePane;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button viewButton;

    @FXML
    private Label typeOfStaffLabel;

    @FXML
    private ChoiceBox<String> typeOfStaffDropdown;

    @FXML
    private ListView<String> listView;

    List<Nurse> nurseList;
    List<Receptionist> receptionistList;
    List<BedBoy> bedBoyList;

    private boolean nurseSelected = false;
    private boolean receptionistSelected = false;
    private boolean wardBoySelected = false;


    private ObservableList<String> items;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nurseList = staffMap.getNurseList();
        receptionistList = staffMap.getReceptionistList();
        bedBoyList = staffMap.getBedBoyList();

        typeOfStaffDropdown.setItems(FXCollections.observableArrayList("Nurse", "Receptionists", "Ward Boys", "Show All"));

        typeOfStaffDropdown.setOnAction(event -> {
            String type = typeOfStaffDropdown.getValue();
            switch (type) {
                case "Nurse" -> {
                    nurseSelected = true;
                    receptionistSelected = false;
                    wardBoySelected = false;

                    typeOfStaffLabel.setText("Nurse Information");

                    listView.getItems().clear();

                    for (Nurse n : nurseList) {
                        listView.getItems().add(n.getName() + "\n" + n.getDepartment());
                    }
                }
                case "Receptionists" -> {
                    receptionistSelected = true;
                    wardBoySelected = false;
                    nurseSelected = false;

                    typeOfStaffLabel.setText("Receptionist Information");

                    listView.getItems().clear();

                    for (Receptionist r : receptionistList) {
                        listView.getItems().add(r.getName() + "\n" + r.getDepartment());
                    }
                }
                case "Ward Boys" -> {
                    wardBoySelected = true;
                    nurseSelected = false;
                    receptionistSelected = false;

                    typeOfStaffLabel.setText("Ward Boy Information");

                    listView.getItems().clear();

                    for (BedBoy b : bedBoyList) {
                        listView.getItems().add(b.getName() + "\n" + b.getDepartment());
                    }
                }
                case "Show All" -> {
                    wardBoySelected = true;
                    nurseSelected = true;
                    receptionistSelected = false;

                    typeOfStaffLabel.setText("All Staff Information");

                    listView.getItems().clear();

                    for (Nurse n : nurseList) {
                        listView.getItems().add(n.getName()  + " (Nurse) \n" + n.getDepartment());
                    }

                    for (Receptionist r : receptionistList) {
                        listView.getItems().add(r.getName() + " (Receptionist)\n" + r.getDepartment());
                    }

                    for (BedBoy b : bedBoyList) {
                        listView.getItems().add(b.getName() + " (Ward Boy)\n" + b.getDepartment());
                    }
                }
            }
        });
    }
}
