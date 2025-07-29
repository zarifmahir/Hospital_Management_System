package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.LoginController;
import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.Networking.Client;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatOfDoctorController {

    @FXML
    private TextField messageArea;

    @FXML
    private ScrollPane scrollWindow;

    @FXML
    private Button sendButton;

    @FXML
    private VBox vBoxOfMessages;

    private Doctor doctor;

    public void setDoctor(Doctor d) {
        this.doctor = d;
    }

    private Client c;


    //@Override
    public void initializeManually() {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        c = new Client(serverAddress, serverPort,doctor.getName());
        c.setType("Doctor");
        c.setObType((Object) doctor);
        c.setVboxOfMessages(vBoxOfMessages);
        vBoxOfMessages.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollWindow.setVvalue((Double) newValue);
        });
    }

    @FXML
    void send(ActionEvent event) {
        String messageToSend = messageArea.getText();
        if(!messageToSend.isEmpty()){
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(5, 5, 5, 5));

            Text text = new Text(messageToSend);
            TextFlow textFlow = new TextFlow(text);

            textFlow.setStyle("-fx-background-color: rgb(15, 125, 242);" + "-fx-color: rgb(239,242,255);" +"-fx-background-radius: 20px;");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hbox.getChildren().add(textFlow);
            vBoxOfMessages.getChildren().add(hbox);

            c.sendMessage(messageToSend);
            messageArea.clear();
        }
    }

    public static void addLabel(String senderName, String messageFromOtherEnd, VBox vBox){
        VBox messageContainer = new VBox();
        messageContainer.setSpacing(1);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        Label nameLabel = new Label("Patient: "+senderName);
        nameLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-padding: 0 0 0 5;");

        Text text = new Text(messageFromOtherEnd);
        text.setFill(Color.BLACK);

        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);
        messageContainer.getChildren().addAll(nameLabel, hBox);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(messageContainer);
            }
        });

    }

//    public static  void main(String[] args){
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//     try{
//        Main main = new Main();
//        main.start(stage);
//     }
//     catch(Exception e){
//         e.printStackTrace();
//     }
//    }
}
