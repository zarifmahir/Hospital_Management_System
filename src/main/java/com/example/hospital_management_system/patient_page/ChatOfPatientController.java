package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.Networking.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

public class ChatOfPatientController implements Initializable{

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
        this.patient = patient;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        c = new Client(serverAddress, serverPort, "zarif1");
        c.setType("Patient");
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

    public static void addLabel(String messageFromOtherEnd, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        Text text = new Text(messageFromOtherEnd);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(15, 125, 242);" + "-fx-color: rgb(233,233,235);" +"-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });

    }
}
