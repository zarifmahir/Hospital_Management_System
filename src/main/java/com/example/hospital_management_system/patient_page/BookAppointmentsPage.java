package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.AppointmentMap;
import com.example.hospital_management_system.doctor_page.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookAppointmentsPage implements Initializable {
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private Button confirmButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> departmentDropdown;

    @FXML
    private ChoiceBox<String> doctorDropdown;

    @FXML
    private Button showTimeSlotsButton;

    @FXML
    private Label timeSlotsLabel;

    @FXML
    private ChoiceBox<String> timeDropdown;

    @FXML
    private Label unfilledLabel;

    @FXML
    private AnchorPane ap;


    List<String> timeList = new ArrayList<>(Arrays.asList("4 pm", "5 pm", "6 pm", "7 pm", "8 pm"));
    ObservableList<String> timeListObservable = FXCollections.observableArrayList(timeList);


    @FXML
    void confirmButtonAction(ActionEvent event) {
        if (departmentDropdown.getValue() == null || doctorDropdown.getValue() == null || timeDropdown.getValue() == null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.getIcons().clear();

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error confirming!");
            alert.initOwner(stage);


            alert.getDialogPane().setHeaderText("Please follow instructions and select required fields!");
            alert.showAndWait();

        }
        else{
            Appointment a = buildAppointment();
            Main.appointmentMap.addAppointment(a);
            writeAppointment(a);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.getIcons().clear();

            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.initOwner(stage);

            alert.getDialogPane().setHeaderText("Appointment Confirmation.");
            alert.getDialogPane().setContentText("You can view your appointments in show appointment page!");
            alert.showAndWait();

            departmentDropdown.setValue(null);
            doctorDropdown.getItems().clear();
            timeSlotsLabel.setText("");
            timeDropdown.getItems().clear();
            datePicker.setValue(null);
            unfilledLabel.setText("");
        }
    }

    @FXML
    void timeSlotButtonAction(ActionEvent event) {
        if (departmentDropdown.getValue() == null || doctorDropdown.getValue() == null || datePicker.getValue() == null) {
            unfilledLabel.setVisible(true);
            timeSlotsLabel.setText("");
            unfilledLabel.setText("Please select all three fields first!");
        }
        else {
            List<String> newTimeList = new ArrayList<>(timeList);

            LocalDate localDate = datePicker.getValue();
            String date = "";

            if (localDate != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = localDate.format(formatter);
            }

            for (String time : timeList) {
                if (Main.appointmentMap.searchAppointment(doctorDropdown.getValue(), date, time)) {
                    newTimeList.remove(time);
                }
            }

            for (String s : newTimeList) {
                System.out.println(s);
            }

            timeListObservable.clear();
            timeListObservable.setAll(newTimeList);
            timeDropdown.setItems(timeListObservable);

            String text = "Available Slots: ";
            if (newTimeList.isEmpty()) {
                text = "No Slots Available";
            }
            else {
                for (String s : newTimeList) {
                    text += s + " ";
                }
            }
            unfilledLabel.setText("");
            timeSlotsLabel.setText(text);
        }
    }

    public Doctor selectedDoctor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> departmentList = FXCollections.observableArrayList("Medicine", "Surgery", "Pediatrics", "Obstetrics", "Gynecology",
                "Orthopedics", "Cardiology", "Neurology", "Pathology", "Psychiatry", "Dermatology");
        departmentDropdown.setItems(departmentList);

        ObservableList<String> doctorList = FXCollections.observableArrayList();

        departmentDropdown.setOnAction(event -> {
            String selectedDepartment = departmentDropdown.getValue();
            if (selectedDepartment != null) {
                List<Doctor> tempDoctorList = Main.doctorsMap.getDepartmentWiseDoctors(selectedDepartment);
                doctorList.clear();
                for (Doctor doctor : tempDoctorList) {
                    doctorList.add(doctor.getName());
                }

                doctorDropdown.setItems(doctorList);
            }
        });

        doctorDropdown.setOnAction(event -> {
            if (departmentDropdown.getValue() == null) {
                unfilledLabel.setText("Select Department First!");
            }
        });

    }

    public Appointment buildAppointment() {
        Random random = new Random();
        int randomId = 1000 + random.nextInt(9000);

        LocalDate localDate = datePicker.getValue();
        String date = "";

        if (localDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = localDate.format(formatter);
        }

        String department = departmentDropdown.getValue();
        int roomNo = 0;

        if (department.equals("Medicine")) {
            roomNo = random.nextInt(50) + 105;
        }
        else if (department.equals("Surgery")) {
            roomNo = random.nextInt(50) + 205;
        }
        else if (department.equals("Pediatrics")) {
            roomNo = random.nextInt(50) + 305;
        }
        else if (department.equals("Obstetrics")) {
            roomNo = random.nextInt(50) + 405;
        }
        else if (department.equals("Gynecology")) {
            roomNo = random.nextInt(50) + 505;
        }
        else if (department.equals("Orthopedics")) {
            roomNo = random.nextInt(50) + 605;
        }
        else if (department.equals("Cardiology")) {
            roomNo = random.nextInt(50) + 705;
        }
        else if (department.equals("Neurology")) {
            roomNo = random.nextInt(50) + 805;
        }
        else if (department.equals("Pathology")) {
            roomNo = random.nextInt(50) + 905;
        }
        else if (department.equals("Psychiatry")) {
            roomNo = random.nextInt(50) + 1005;
        }
        else if (department.equals("Dermatology")) {
            roomNo = random.nextInt(50) + 1105;
        }

        return new Appointment(doctorDropdown.getValue(), patient.getName(), date, randomId, timeDropdown.getValue(), department, roomNo);
    }

    private void writeAppointment(Appointment a) {
        try {
            String s = a.getDoctorName() + "<" + a.getPatientName() + "<" + a.getDate() + "<" + a.getId() + "<" + a.getTime() + "<" + a.getDepartment() + "<" + a.getRoomNo();
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/texts/AppointmentList.txt", true));
            bw.write(s);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
