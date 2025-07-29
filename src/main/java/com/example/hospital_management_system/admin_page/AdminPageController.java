package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class AdminPageController {

    public Button patientPanelButton;
    @FXML
    private Button adminDashboardButton;

    @FXML
    private BorderPane bp;

    @FXML
    private Button doctorsPanelButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button menuButton2;

    @FXML
    private Button reportsButton;

    @FXML
    private VBox slider;

    @FXML
    private Button systemSettingsButton;

    @FXML
    private Button staffManagementButton;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    void loadPage(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();

        if(page.equals("patients_panel")){
            PatientPanelController controller = loader.getController();
            controller.setMain(main);
        }
        else if(page.equals("doctors_panel")){
            DoctorPanelController controller = loader.getController();
            controller.setMain(main);
        }
        else if (page.equals("admin_dashboard")){
            AdminDashboardController controller = loader.getController();
            controller.setAdmin(admin);
            controller.adminPageController = this;
        }

        bp.setCenter(root);
    }

    @FXML
    void adminDashboard(ActionEvent event) {
        try {
            loadPage("admin_dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void doctorsPanelPressed(ActionEvent event) {
        try {
            loadPage("doctors_panel");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void staffPressed(ActionEvent event) {
        try {
            loadPage("staff_page");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try {
            boolean status = Main.showLogOutAlert();
            if (status) {main.showLoginPage();}
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void patient_appointments(MouseEvent event) {

    }

    @FXML
    void patient_dashboard(MouseEvent event) {

    }

    @FXML
    void patient_history(MouseEvent event) {

    }

    @FXML
    void reports(ActionEvent event) {
        try {
            loadPage("show_all_prescriptions");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void patientPanel(ActionEvent actionEvent) {
        try {
            loadPage("patients_panel");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showAppointments(ActionEvent event) {
        try {
            loadPage("appointment_page");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void dashboardHover(MouseEvent event) {
        adminDashboardButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void dashboardHoverExited(MouseEvent event) {
        adminDashboardButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void dashboardPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), adminDashboardButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void dashboardReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), adminDashboardButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    @FXML
    void doctorPanelHover(MouseEvent event) {
        doctorsPanelButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void doctorPanelHoverExited(MouseEvent event) {
        doctorsPanelButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void doctorPanelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), doctorsPanelButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void doctorPanelReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), doctorsPanelButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    @FXML
    void logoutHover(MouseEvent event) {
        logoutButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void logoutHoverExited(MouseEvent event) {
        logoutButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void logoutPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), logoutButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void logoutReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), logoutButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    @FXML
    void menuHover(MouseEvent event) {
        menuButton2.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void menuHoverExited(MouseEvent event) {
        menuButton2.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void menuPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), menuButton2);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void menuReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), menuButton2);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    @FXML
    void patientHover(MouseEvent event) {
        patientPanelButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void patientHoverExited(MouseEvent event) {
        patientPanelButton.setStyle("-fx-background-color: transparent;");
    }


    @FXML
    void patientPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), patientPanelButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void patientReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), patientPanelButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    @FXML
    void reportsHover(MouseEvent event) {
        reportsButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void reportsHoverExited(MouseEvent event) {
        reportsButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void reportsPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), reportsButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void reportsReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), reportsButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    @FXML
    void userHover(MouseEvent event) {
        staffManagementButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void userHoverExited(MouseEvent event) {
        staffManagementButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void userPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), staffManagementButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void userReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), staffManagementButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setAdmin(Admin a) {
        this.admin = a;
    }


    @FXML
    private Button appointmentButton;

    @FXML
    void appointmentHover(MouseEvent event) {
        appointmentButton.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void appointmentHoverExited(MouseEvent event) {
        appointmentButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void appointmentPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), appointmentButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void appointmentReleased(MouseEvent event) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), appointmentButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }


    @FXML
    private VBox leftPane;

    @FXML
    void toggleMenu(ActionEvent event) {
        if (bp.getLeft() != null) {
            bp.setLeft(null);
            bp.setRight(null);
        } else {
            bp.setLeft(leftPane);
        }
    }

    public Admin admin;

}
