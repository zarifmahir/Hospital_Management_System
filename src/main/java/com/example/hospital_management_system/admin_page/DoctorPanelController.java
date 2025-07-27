package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.doctor_page.DoctorsMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorPanelController implements Initializable {

    public Button editButton;
    public Label selectionLabel;
    public Button deleteButton;
    public Button viewButton;
    @FXML
    private TableView<Doctor> doctorsPanel;
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

    private boolean selectedStatus;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    //@FXML
   // private TableColumn<Doctor, String> status;

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
        //status.setCellValueFactory(new PropertyValueFactory<Doctor, String>("status"));

        List<Doctor> doctorList = Main.doctorsMap.getDoctorList();
        for(Doctor doctor:doctorList){
            doctorsPanel.getItems().add(doctor);
        }

        doctorsPanel.setEditable(false);

        disableButtons();
        doctorsPanel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                enableButtons();
                System.out.println("Hi");
            }
            else{
                disableButtons();
                System.out.println("BYe");
            }
        });

    }

    private void disableButtons(){
         selectedStatus = false;
        selectionLabel.setVisible(true);
        editButton.setDisable(true);
        viewButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
        deleteButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
        viewButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");



    }

    private void enableButtons(){
        doctorsPanel.setEditable(true);
        selectedStatus = true;
        selectionLabel.setVisible(false);
        editButton.setDisable(false);
        viewButton.setDisable(false);
        deleteButton.setDisable(false);
        editButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        deleteButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        viewButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
    }

    public void edit(ActionEvent actionEvent) {
    }

    public void view(ActionEvent actionEvent) throws IOException {
        if (selectedStatus) {
            Doctor doctor = doctorsPanel.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("doctor_popup.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            DoctorPopupController controller = fxmlLoader.getController();
            controller.setDoctor(doctor);

            Stage stage = new Stage();
            stage.setTitle("Doctor Information");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        if(selectedStatus) {
            System.out.println("Hello");
            int serial = 0, i=0;
            String deleteName = doctorsPanel.getSelectionModel().getSelectedItem().getName();
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/DoctorsList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    lines.add(line);
                    String[] s =  line.split("<");
                    if(s[0].equals(deleteName)){serial=i;}
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println(lines.size());
            System.out.println(serial);

            lines.remove(serial);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/DoctorsList.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            doctorsPanel.getItems().remove(doctorsPanel.getSelectionModel().getSelectedItem());
            doctorsPanel.getSelectionModel().clearSelection();
            main.loadDoctors();


        }
    }
}
