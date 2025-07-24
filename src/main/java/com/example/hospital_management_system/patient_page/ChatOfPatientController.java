package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.Networking.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatOfPatientController {

    @FXML
    private TextField messageArea;

    @FXML
    private ScrollPane scrollWindow;

    @FXML
    private Button sendButton;

    @FXML
    private VBox vBoxOfMessages;

    private Patient patient;



    private Client c;
    public void setPatient(Patient patient) {
        System.out.println("setPatient");
        this.patient = patient;
    }


    //@Override
    public void initializeManually() {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        c = new Client(serverAddress, serverPort, patient.getName());
        c.setType("Patient");
        c.setObType((Object) patient);
        createVBoxOfMessages(patient, vBoxOfMessages);
        c.setVboxOfMessages(vBoxOfMessages);
        vBoxOfMessages.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollWindow.setVvalue((Double) newValue);
        });

    }

    void createVBoxOfMessages(Patient patient, VBox vBoxOfMessages) {
        if(Main.patientChatMap.containsChat(patient)) {
            patient.setMyChat(Main.patientChatMap.getChat(patient));
            String[] chats = patient.getMyChat().split("<");
            for(int i = 0; i < chats.length; i++) {
                String[] parts = chats[i].split("~");
                if(parts[1].equals("R")){
                    generateSendingMessage(parts[0], vBoxOfMessages);
                    //System.out.println("Done");
                }
                else if(parts[1].equals("L")){
                    generateReceivingMessage(parts[0], vBoxOfMessages);
                    //System.out.println("Done");
                }
            }

        }
        else{
            System.out.println("No previous chat");
        }

    }

    void generateSendingMessage(String message, VBox vBoxOfMessages) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(5, 5, 5, 5));

        Text text = new Text(message);
        text.setFill(Color.color(0.934, 0.945, 0.996));
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(15, 125, 242);" +"-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));


        hbox.getChildren().add(textFlow);
        vBoxOfMessages.getChildren().add(hbox);
    }

    void generateReceivingMessage(String message, VBox vBoxOfMessages) {
        VBox messageContainer = new VBox();
        messageContainer.setSpacing(1);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        Label nameLabel = new Label("Doctor");
        nameLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-padding: 0 0 0 5;");

        Text text = new Text(message);
        text.setFill(Color.BLACK);

        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);

        messageContainer.getChildren().addAll(nameLabel, hBox);
        vBoxOfMessages.getChildren().add(messageContainer);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//
////                System.out.println(vBox);
//            }
//        });
    }




    @FXML
    void send(ActionEvent event) {
        String messageToSend = messageArea.getText();
        if(!messageToSend.isEmpty()){
            generateSendingMessage(messageToSend, vBoxOfMessages);


            if(patient.getMyChat().isEmpty()){
                patient.setMyChat(messageToSend+"~R");
            }
            else patient.setMyChat(patient.getMyChat() + "<"+messageToSend+"~R");
            c.sendMessage(messageToSend);
            messageArea.clear();
        }

    }

    public static void addLabel(String messageFromOtherEnd, VBox vBox, Object p){
        VBox messageContainer = new VBox();
        messageContainer.setSpacing(1);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        Label nameLabel = new Label("Doctor");
        nameLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-padding: 0 0 0 5;");

        Text text = new Text(messageFromOtherEnd);
        text.setFill(Color.BLACK);

        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);

        messageContainer.getChildren().addAll(nameLabel, hBox);
        Patient ptn = (Patient)p;
        ptn.setMyChat(ptn.getMyChat()+"<"+messageFromOtherEnd+"~L");
        //vBox.getChildren().add(messageContainer);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(messageContainer);
                System.out.println(vBox);
            }
        });

    }
}
