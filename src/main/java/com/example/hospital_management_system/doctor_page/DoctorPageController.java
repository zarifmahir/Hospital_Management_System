package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.patient_page.ChatOfPatientController;
import com.example.hospital_management_system.patient_page.PatientDashboardController;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class DoctorPageController {

    public Button menuButton;
    public Button logoutButton;

    @FXML
    private BorderPane doctorBorderPane;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button patientsButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button chatButton;

    @FXML
    private Main main;
    private Doctor doctor;

    @FXML
    private void loadPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();

        if(page.equals("doctor_dashboard")) {
            DoctorDashboardController controller = loader.getController();
            controller.setDoctor(doctor);
        }
        else if(page.equals("chat_of_doctor")) {
            ChatOfDoctorController controller = loader.getController();
            controller.setDoctor(doctor);
        }

        doctorBorderPane.setCenter(root);
    }

    @FXML
    void doctor_dashboard(MouseEvent event) {
        try {
            loadPage("doctor_dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void doctor_patient_history(MouseEvent event) {
        try {
            loadPage("doctor_patient_history");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void doctor_schedule(MouseEvent event) {
        try {
            loadPage("doctor_schedule");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @FXML
    void logOut(ActionEvent event) throws Exception {
        main.showLoginPage();
    }

    @FXML
    public void setMain(Main main) {
        this.main = main;
    }

    public void setOnMousePressed(MouseEvent mouseEvent) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), dashboardButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    public void setOnMouseReleased(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), dashboardButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setOnMousePressed1(MouseEvent mouseEvent) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), scheduleButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    public void setOnMouseReleased1(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), scheduleButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setOnMousePressed2(MouseEvent mouseEvent) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), patientsButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    public void setOnMouseReleased2(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), patientsButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setOnMousePressed3(MouseEvent mouseEvent) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), menuButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    public void setOnMouseReleased3(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), menuButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setOnMousePressed4(MouseEvent mouseEvent) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), logoutButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    public void setOnMouseReleased4(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), logoutButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setDoctor(Doctor d) {
        this.doctor = d;
    }

    public void dashboardHover(MouseEvent mouseEvent) {
        dashboardButton.setStyle("-fx-background-color: lightgray;");
    }

    public void dashboardHoverExited(MouseEvent mouseEvent) {
        dashboardButton.setStyle("-fx-background-color: transparent;");
    }

    public void scheduleHover(MouseEvent mouseEvent) {
        scheduleButton.setStyle("-fx-background-color: lightgray;");
    }

    public void scheduleHoverExited(MouseEvent mouseEvent) {
        scheduleButton.setStyle("-fx-background-color: transparent;");
    }

    public void patientsHover(MouseEvent mouseEvent) {
        patientsButton.setStyle("-fx-background-color: lightgray;");
    }

    public void patientsHoverExited(MouseEvent mouseEvent) {
        patientsButton.setStyle("-fx-background-color: transparent;");
    }

    public void logoutHover(MouseEvent mouseEvent) {
        logoutButton.setStyle("-fx-background-color: lightgray;");
    }

    public void logoutHoverExited(MouseEvent mouseEvent) {
        logoutButton.setStyle("-fx-background-color: transparent;");
    }

    public void menuHover(MouseEvent mouseEvent) {
        menuButton.setStyle("-fx-background-color: lightgray;");
    }

    public void menuHoverExited(MouseEvent mouseEvent) {
        menuButton.setStyle("-fx-background-color: transparent;");
    }


    @FXML
    void chatHover(MouseEvent event) {
        chatButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void chatHoverExited(MouseEvent event) {
        chatButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void chatPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), chatButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void chatReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), chatButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }


        @FXML
    private VBox leftPane;

    @FXML
    void toggleMenu(ActionEvent event) {
        if (doctorBorderPane.getLeft() != null) {
            doctorBorderPane.setLeft(null);
            doctorBorderPane.setRight(null);
        } else {
            doctorBorderPane.setLeft(leftPane);
        }
    }

    public void chat(ActionEvent actionEvent) {
        try {
            loadPage("chat_of_doctor");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
