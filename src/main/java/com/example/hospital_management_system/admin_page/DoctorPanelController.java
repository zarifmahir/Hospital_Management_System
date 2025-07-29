package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.doctor_page.DoctorsMap;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.register_page.RegistrationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DoctorPanelController implements Initializable {

    public Button editButton;
    public Label selectionLabel;
    public Button deleteButton;
    public Button viewButton;
    public AnchorPane editPane;
    public TextField editId;
    public TextField editName;
    public ChoiceBox<String> editDepartment;
    public TextField editPhone;
    public TextField editRoom;
    public ImageView newImage;
    public TextField editEmail;
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
    private boolean isEdited = false;

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

        editPane.setVisible(false);
        List<Doctor> doctorList = Main.doctorsMap.getDoctorList();
        for(Doctor doctor:doctorList){
            doctorsPanel.getItems().add(doctor);
        }

        Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=oldValue){
                doctorsPanel.getItems().clear();
                List<Doctor> doctorlist2 = Main.doctorsMap.getDoctorList();
                for(Doctor p: doctorlist2){
                    doctorsPanel.getItems().add(p);
                }
                doctorsPanel.setEditable(false);
            }
        });

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
        if(selectedStatus){
            editPane.setVisible(true);
            Doctor doctor = doctorsPanel.getSelectionModel().getSelectedItem();
            editName.setPromptText(doctor.getName());
            ObservableList<String> list = FXCollections.observableArrayList("Medicine", "Surgery", "Pediatrics", "Obstetrics", "Gynecology",
                    "Orthopedics", "Cardiology", "Neurology", "Pathology", "Psychiatry", "Dermatology");
            editDepartment.setItems(list);
            editId.setPromptText(doctor.getId());
            editPhone.setPromptText(doctor.getMobile());
            editRoom.setPromptText(doctor.getRoom());
            editEmail.setPromptText(doctor.getEmail());
            String img = "/images/user.png";
            if(!doctor.getImage().equals("null")){ img = doctor.getImage();}
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
            newImage.setImage(image);
        }
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
            doctorsPanel.getSelectionModel().select(doctor);
        }
    }

    public void delete(ActionEvent actionEvent) throws IOException {
        if(selectedStatus && showDeleteAlert()) {
            int serial = 0, i=0;
            String deleteName = doctorsPanel.getSelectionModel().getSelectedItem().getId();
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/DoctorsList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    lines.add(line);
                    String[] s =  line.split("\\|");
                    if(s[0].equals(deleteName)){
                        serial=i;
                        synchronized (Main.c){
                            Main.c.sendMessage("DoctorsList$Remove$"+serial);
                        }
                    }
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
            selectedStatus = false;

        }
    }

    public boolean showDeleteAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deletion Confirmation");
        alert.setHeaderText("Are you sure you want to delete this person?");
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType2 = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType, buttonType2);
        alert.showAndWait();
        return alert.getResult() != buttonType2;

    }
    public boolean showEditAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Edit Confirmation");
        alert.setHeaderText("Are you sure you want to edit the data?");
        ButtonType buttonType = new ButtonType("Yes");
        ButtonType buttonType2 = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonType, buttonType2);
        alert.showAndWait();
        return alert.getResult() != buttonType2;

    }

    public void showSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success Edit");
        alert.show();
    }

    public void editImage(ActionEvent actionEvent) {
        Doctor doctor = doctorsPanel.getSelectionModel().getSelectedItem();
        String img = "/images/user.png";

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Image image = new Image(file.toURI().toString());
                //System.out.println(file.toURI().toString());
                img = "/images/"+file.getName();
                doctor.setImage(img);

                // Set it to the ImageView
                newImage.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void apply(ActionEvent actionEvent) throws IOException {
        if(selectedStatus && showEditAlert()){
            Doctor doctor = doctorsPanel.getSelectionModel().getSelectedItem();
            String prevId = doctor.getId();
            if(!editId.getText().isEmpty()) doctor.setId(editId.getText());
            if(!editName.getText().isEmpty()) doctor.setName(editName.getText());
            if(!editPhone.getText().isEmpty()) doctor.setMobile(editPhone.getText());
            if(!editRoom.getText().isEmpty()) doctor.setRoom(editRoom.getText());
            if(!editEmail.getText().isEmpty()) doctor.setEmail(editEmail.getText());
            if(editDepartment.getValue() != null){
                doctor.setDepartment(editDepartment.getValue());
            }

            int serial = 0;
            int i=0;
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/DoctorsList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    lines.add(line);
                    String[] s =  line.split("\\|");
                    if(s[0].equals(prevId)){
                        serial=i;
                        synchronized (Main.c){
                            Main.c.sendMessage("DoctorsList$Remove$"+serial);
                        }
                    }
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
            RegistrationController.writeDoctor(doctor);
            doctorsPanel.getItems().remove(doctorsPanel.getSelectionModel().getSelectedItem());
            main.loadDoctors();
            doctorsPanel.getItems().add(Main.doctorsMap.getDoctorById(doctor.getId()));
            isEdited = true;
        }
    }

    public void ok(ActionEvent actionEvent) {
        if(selectedStatus){
            if(isEdited) showSuccessAlert();
            editPane.setVisible(false);
            doctorsPanel.getSelectionModel().clearSelection();
        }

    }

    public void cancel(ActionEvent actionEvent) {
        if(selectedStatus){
            editPane.setVisible(false);
            doctorsPanel.getSelectionModel().clearSelection();
        }
    }
}
