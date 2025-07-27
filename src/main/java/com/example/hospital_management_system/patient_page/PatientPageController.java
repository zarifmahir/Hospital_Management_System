package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PatientPageController {
    @FXML
    private BorderPane bp;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button appointmentsButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Main main;

    public VBox leftPane;

    private Patient patient;

    private String currentPage;



    public void setPatient(Patient patient) {
        System.out.println("setPatient");
        this.patient = patient;
        this.currentPage = "others";
    }


    private void loadPage(String page) throws IOException {

        if(currentPage.equals("chat_of_patient")){
            Main.patientChatMap.addChat(patient, patient.getMyChat());
            currentPage = "others";
        }
        bp.setCenter(null);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(page + ".fxml"));
        Parent root = loader.load();

        if(page.equals("patient_dashboard")) {
            PatientDashboardController controller = loader.getController();
            controller.setPatient(patient);
        }
        else if(page.equals("chat_of_patient")) {
            System.out.println("chat_of_patient");
            ChatOfPatientController controller = loader.getController();
            controller.setPatient(patient);
            controller.initializeManually();
           // System.out.println("in page controller");

        }
        else if(page.equals("patient_appointments")) {
            PatientAppointmentsController controller = loader.getController();
            controller.setPatient(patient);
        }

        bp.setCenter(root);
    }

    public void patient_appointments(MouseEvent mouseEvent) {
        try {
            loadPage("patient_appointments");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void patient_dashboard(MouseEvent mouseEvent) {
        try {
            loadPage("patient_dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void patient_history(MouseEvent mouseEvent) {
        try {
            loadPage("patient_history");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void chat(ActionEvent actionEvent) {
        try {

            loadPage("chat_of_patient");
            currentPage = "chat_of_patient";
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public void setMain(Main main) {
        this.main = main;
    }

    public static void reloadPatientChats(Patient patient) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/ChatHistoryOfPatients.txt"));
        writer.write("");
        writer.close();
        boolean stat = false;
        for(Patient p: Main.patientChatMap.chatMap.keySet()){
            writeChats(p);
            if(p.getName().equals(patient.getName())) stat = true;
        }
        if(!stat) writeChats(patient);
        System.out.println("Written successfully");
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            reloadPatientChats(patient);
           main.showLoginPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   private static void writeChats(Patient patient) throws IOException {
        try {
            String content = patient.getUsername()+"@"+patient.getPass()+"|"+patient.getMyChat();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/texts/ChatHistoryOfPatients.txt", true));
            writer.write(content);
            writer.newLine();
            writer.close();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

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
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), appointmentsButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }


    public void setOnMouseReleased1(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), appointmentsButton);
        scaleUp.setToX(1);
        scaleUp.setToY(1);
        scaleUp.play();
    }

    public void setOnMousePressed2(MouseEvent mouseEvent) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), historyButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    public void setOnMouseReleased2(MouseEvent mouseEvent) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), historyButton);
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

    public void appointmentHover(MouseEvent mouseEvent) {
        appointmentsButton.setStyle("-fx-background-color: lightgray;");
    }

    public void dashboardHover(MouseEvent mouseEvent) {
        dashboardButton.setStyle("-fx-background-color: lightgray;");
    }

    public void historyHover(MouseEvent mouseEvent) {
        historyButton.setStyle("-fx-background-color: lightgray;");
    }

    public void dashboardHoverExited(MouseEvent mouseEvent) {
        dashboardButton.setStyle("-fx-background-color: transparent;");
    }

    public void appointmentHoverExited(MouseEvent mouseEvent) {
        appointmentsButton.setStyle("-fx-background-color: transparent;");
    }

    public void historyHoverExited(MouseEvent mouseEvent) {
        historyButton.setStyle("-fx-background-color: transparent;");
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

    private boolean isLeftVisible = true;

    @FXML
    void toggleMenu(ActionEvent event) {
        if (isLeftVisible) {
            bp.setLeft(null);
            bp.setRight(null);
        } else {
            bp.setLeft(leftPane);
        }
        isLeftVisible = !isLeftVisible;
    }


}
