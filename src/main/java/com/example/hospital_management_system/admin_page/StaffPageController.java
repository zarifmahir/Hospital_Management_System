package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.Staff.BedBoy;
import com.example.hospital_management_system.Staff.Nurse;
import com.example.hospital_management_system.Staff.Receptionist;
import com.example.hospital_management_system.Staff.Staff;
import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.register_page.RegistrationController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.hospital_management_system.Main.staffMap;

public class StaffPageController implements Initializable {

    public Label typeOfStaffLabel1;
    public AnchorPane editPane;
    public TextField editName;
    public TextField editDepartment;
    public ImageView newImage;
    public TextField editPhone;
    public TextField editYeOfExp;
    public TextField editRole;
    public TextField editEmail;
    public TextField editAddress;
    public AnchorPane addPane;
    public TextField addName;
    public TextField addDepartment;
    public ImageView addImage;
    public TextField addPhone;
    public TextField addYearsOfExp;
    public TextField addRole;
    public TextField addEmail;
    public TextField addAddress;
    public Label errorMessage;
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

    private boolean selectedStatus;

    List<Nurse> nurseList;
    List<Receptionist> receptionistList;
    List<BedBoy> bedBoyList;

    private boolean nurseSelected = false;
    private boolean receptionistSelected = false;
    private boolean wardBoySelected = false;


    ObservableList<String> items = FXCollections.observableArrayList();


    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }

    private boolean isEdited = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nurseList = staffMap.getNurseList();
        receptionistList = staffMap.getReceptionistList();
        bedBoyList = staffMap.getBedBoyList();

        typeOfStaffDropdown.setItems(FXCollections.observableArrayList("Nurse", "Receptionists", "Ward Boys", "Show All"));

        //initially show all types of staff
        wardBoySelected = true;
        nurseSelected = true;
        receptionistSelected = true;
        errorMessage.setVisible(false);
        addPane.setVisible(false);

        typeOfStaffLabel.setText("All Staff Information");

        listView.getItems().clear();

        for (Nurse n : nurseList) {
            listView.getItems().add(n.getName()  + " (Nurse) \n" + n.getDepartment());
            items.add(n.getName()  + " (Nurse) \n" + n.getDepartment());
        }

        for (Receptionist r : receptionistList) {
            listView.getItems().add(r.getName() + " (Receptionist)\n" + r.getDepartment());
            items.add(r.getName() + " (Receptionist)\n" + r.getDepartment());
        }

        for (BedBoy b : bedBoyList) {
            listView.getItems().add(b.getName() + " (Ward Boy)\n" + b.getDepartment());
            items.add(b.getName() + " (Ward Boy) \n" + b.getDepartment());
        }

        typeOfStaffDropdown.setOnAction(event -> {
            String type = typeOfStaffDropdown.getValue();
            items.clear();
            switch (type) {
                case "Nurse" -> {
                    nurseSelected = true;
                    receptionistSelected = false;
                    wardBoySelected = false;

                    typeOfStaffLabel.setText("Nurse Information");

                    listView.getItems().clear();

                    for (Nurse n : nurseList) {
                        listView.getItems().add(n.getName() + "\n" + n.getDepartment());
                        items.add(n.getName() + "\n" + n.getDepartment());
                    }

                    Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
                        listView.getItems().clear();
                        System.out.println("Here in the property");
                        List<Nurse> nnewList = Main.staffMap.getNurseList();
                        for (Nurse n : nnewList) {
                            listView.getItems().add(n.getName() + "\n" + n.getDepartment());
                            items.add(n.getName() + "\n" + n.getDepartment());
                        }
                    });
                }
                case "Receptionists" -> {
                    receptionistSelected = true;
                    wardBoySelected = false;
                    nurseSelected = false;

                    typeOfStaffLabel.setText("Receptionist Information");

                    listView.getItems().clear();

                    for (Receptionist r : receptionistList) {
                        listView.getItems().add(r.getName() + "\n" + r.getDepartment());
                        items.add(r.getName() + "\n" + r.getDepartment());
                    }
                    Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
                        listView.getItems().clear();
                        System.out.println("Here in the property2");
                        List<Receptionist> rnewList = staffMap.getReceptionistList();
                        for (Receptionist n : rnewList) {
                            listView.getItems().add(n.getName() + "\n" + n.getDepartment());
                            items.add(n.getName() + "\n" + n.getDepartment());
                        }
                    });

                }
                case "Ward Boys" -> {
                    wardBoySelected = true;
                    nurseSelected = false;
                    receptionistSelected = false;

                    typeOfStaffLabel.setText("Ward Boy Information");

                    listView.getItems().clear();

                    for (BedBoy b : bedBoyList) {
                        listView.getItems().add(b.getName() + "\n" + b.getDepartment());
                        items.add(b.getName() + "\n" + b.getDepartment());
                    }

                    Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
                        listView.getItems().clear();
                        System.out.println("Here in the property3");
                        List<BedBoy> rnewList = staffMap.getBedBoyList();
                        for (BedBoy n : rnewList) {
                            listView.getItems().add(n.getName() + "\n" + n.getDepartment());
                            items.add(n.getName() + "\n" + n.getDepartment());
                        }
                    });
                }
                case "Show All" -> {
                    wardBoySelected = true;
                    nurseSelected = true;
                    receptionistSelected = true;

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
                    Main.isUpdatedProperty().addListener((observable, oldValue, newValue) -> {
                        listView.getItems().clear();
                        System.out.println("Here in the property4");
                        for (Nurse n : nurseList) {
                            listView.getItems().add(n.getName()  + " (Nurse) \n" + n.getDepartment());
                        }

                        for (Receptionist r : receptionistList) {
                            listView.getItems().add(r.getName() + " (Receptionist)\n" + r.getDepartment());
                        }

                        for (BedBoy b : bedBoyList) {
                            listView.getItems().add(b.getName() + " (Ward Boy)\n" + b.getDepartment());
                        }
                    });
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                enableButtons();
                System.out.println("Hi");
            }
            else{
                disableButtons();
                System.out.println("BYe");
            }
        });

        searchBarField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<String> filteredItems = FXCollections.observableArrayList();

            for (String items : items) {
                if (items.toLowerCase().contains(newValue.toLowerCase())) {
                    filteredItems.add(items);
                }
            }

            listView.getItems().clear();
            listView.getItems().addAll(filteredItems);
        });


    }

    private void enableButtons() {
        selectedStatus = true;
        viewButton.setDisable(false);
        removeButton.setDisable(false);
        editButton.setDisable(false);
//        addButton.setDisable(false);
        editButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        removeButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        viewButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
        //addButton.setStyle("-fx-background-color: #2196F3;" + "-fx-text-fill: white;" + "-fx-opacity: 1.5;");
    }

    private void disableButtons(){
        selectedStatus = false;
        editButton.setDisable(true);
        viewButton.setDisable(true);
        removeButton.setDisable(true);
        //addButton.setDisable(true);
        editButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
        removeButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
        //viewButton.setStyle("-fx-background-color: lightgray;" + "-fx-text-fill: darkgray;" + "-fx-opacity: 1.0;");
    }


    @FXML
    private TextField searchBarField;

    public void viewStaff(ActionEvent actionEvent) throws IOException {
        if (selectedStatus) {
            String person = listView.getSelectionModel().getSelectedItem();
            String[] temp = person.split(" ");
            Staff staff = null;
            if(staffMap.getNurseByName(temp[0])!=null){
                staff = staffMap.getNurseByName(temp[0]);
            }
            else if(staffMap.getBedBoyByName(temp[0])!=null){
                staff = staffMap.getBedBoyByName(temp[0]);
            }
            else if(staffMap.getReceptionistByName(temp[0])!=null){
                staff = staffMap.getReceptionistByName(temp[0]);
            }
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patient_popup.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//
//            PatientPopupController controller = fxmlLoader.getController();
//            controller.setPatient(patient);
//
//            Stage stage = new Stage();
//            stage.setTitle("Patient Information");
//            stage.setScene(new Scene(root));
//            stage.show();
        }
    }
    public  boolean checkEr(){
        if(addName.getText().isEmpty() || addDepartment.getText().isEmpty() || addEmail.getText().isEmpty() || addPhone.getText().isEmpty() || addAddress.getText().isEmpty()
                || addYearsOfExp.getText().isEmpty() || addRole.getText().isEmpty()){
            return true;
        }
        return false;
    }






    public void addStaff(ActionEvent actionEvent) throws IOException {


            errorMessage.setVisible(false);
//            name, dept, years, phone, email, address
            addPane.setVisible(true);
            disableButtons();


    }

    public void editStaff(ActionEvent actionEvent) {
        if(selectedStatus){
            editPane.setVisible(true);
            String person = listView.getSelectionModel().getSelectedItem();
            String[] temp = person.split(" \\(");
            System.out.println(temp[0]);
            Staff staff = null;
            if(staffMap.getNurseByName(temp[0])!=null){
                 staff = staffMap.getNurseByName(temp[0]);
            }
            else if(staffMap.getBedBoyByName(temp[0])!=null){
                 staff = staffMap.getBedBoyByName(temp[0]);
            }
            else if(staffMap.getReceptionistByName(temp[0])!=null){
                 staff = staffMap.getReceptionistByName(temp[0]);
            }
            editName.setPromptText(staff.getName());
            editPhone.setPromptText(staff.getPhoneNumber());
            editDepartment.setPromptText(staff.getDepartment());
            editEmail.setPromptText(staff.getEmail());
            editYeOfExp.setPromptText(staff.getYearsOfExperience());
            editAddress.setPromptText(staff.getAddress());
            editRole.setPromptText(staff.getRole());

            String img = "/images/user.png";
//            if(!patient.getImage().equals("null")){ img = patient.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
            newImage.setImage(image);
        }
    }

    public void removeStaff(ActionEvent actionEvent) throws IOException {
        if(selectedStatus && showDeleteAlert()) {
            System.out.println("Hello");
            int serial = 0, i=0;
            String deleteName = listView.getSelectionModel().getSelectedItem().split(" \\(")[0];
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/StaffList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    String[] s =  line.split("\\|");
                    if(s[1].equals(deleteName)){
                        serial=i;
                        synchronized (Main.c){
                            Main.c.sendMessage("StaffList$Remove$"+serial);
                        }
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            System.out.println(serial);
            lines.remove(serial);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/StaffList.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            listView.getSelectionModel().clearSelection();
            main.loadStaff();
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
    }

    public void cancel(ActionEvent actionEvent) {
        if(selectedStatus){
            editPane.setVisible(false);
            listView.getSelectionModel().clearSelection();
        }
    }

    public void apply(ActionEvent actionEvent) throws IOException {
        if(selectedStatus && showEditAlert()){
            String person = listView.getSelectionModel().getSelectedItem();
            String[] temp = person.split(" \\(");
            Staff staff = null;
            if(staffMap.getNurseByName(temp[0])!=null){
                staff = staffMap.getNurseByName(temp[0]);
            }
            else if(staffMap.getBedBoyByName(temp[0])!=null){
                staff = staffMap.getBedBoyByName(temp[0]);
            }
            else if(staffMap.getReceptionistByName(temp[0])!=null){
                staff = staffMap.getReceptionistByName(temp[0]);
            }
            String prevName = temp[0];

            if(!editName.getText().isEmpty()) staff.setName(editName.getText());
            if(!editPhone.getText().isEmpty()) staff.setPhoneNumber(editPhone.getText());
            if(!editEmail.getText().isEmpty()) staff.setEmail(editEmail.getText());
           if(!editDepartment.getText().isEmpty()) staff.setDepartment(editDepartment.getText());
           if(!editAddress.getText().isEmpty()) staff.setAddress(editAddress.getText());
           if(!editYeOfExp.getText().isEmpty()) staff.setYearsOfExperience(editYeOfExp.getText());
           if(!editRole.getText().isEmpty()) staff.setRole(editRole.getText());


           System.out.println("Hello");
            int serial = 0, i=0;
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/texts/StaffList.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    String[] s =  line.split("\\|");
                    if(s[1].equals(prevName)){
                        serial=i;
                        synchronized (Main.c){
                            Main.c.sendMessage("StaffList$Remove$"+serial);
                        }
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            System.out.println(serial);
            lines.remove(serial);

            String content = staff.getRole()+"|"+staff.getName()+"|"+staff.getDepartment()+"|"+staff.getYearsOfExperience()+"|"+staff.getPhoneNumber()+"|"+staff.getEmail()+"|"+staff.getAddress();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/StaffList.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.write(content);
                writer.newLine();

            }

            listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            listView.getSelectionModel().clearSelection();;
            main.loadStaff();
            Main.setUpdated(true);
            System.out.println("Here1");
            selectedStatus = true;
            isEdited = true;
        }
    }

    public void ok(ActionEvent actionEvent) {
        if(selectedStatus){
            if(isEdited) showSuccessAlert();
            System.out.println("Here22");
            editPane.setVisible(false);
            listView.getSelectionModel().clearSelection();
        }
    }

    public void cancelNew(ActionEvent actionEvent) {
        addPane.setVisible(false);
    }

    public void addNew(ActionEvent actionEvent) throws IOException {
        if(checkEr()){
            errorMessage.setVisible(true);
        }
        else{
            String content = addRole.getText() + "|" + addName.getText() + "|" + addDepartment.getText() + "|" + addYearsOfExp.getText() + "|" + addPhone.getText() + "|" + addEmail.getText() + "|" + addAddress.getText();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/StaffList.txt", true));
                writer.write(content);
                writer.newLine();
                writer.close();
                synchronized (Main.c) {
                    System.out.println("Staff writng");
                    Main.c.sendMessage("StaffList$" + content);
                }

                System.out.println("File written successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            main.loadStaff();
            Platform.runLater(() -> {
                Main.setUpdated(!Main.getUpdated()); // Will trigger listener
            });
            showSuccessAlert();
            addPane.setVisible(false);
        }

    }
}
