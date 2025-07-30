package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.Networking.Client;
import com.example.hospital_management_system.patient_page.Patient;
import com.example.hospital_management_system.patient_page.PatientPageController;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class ResidentPage extends Application implements Initializable {

    public AnchorPane messagePane;
    public Button logoutButton;
    @FXML
    private TextField messageArea;

    @FXML
    private VBox patientsBox;

    @FXML
    private ScrollPane scrollWindow;

    @FXML
    private Button sendButton;

    @FXML
    private VBox vBoxOfMessages;

    private Client c;
    private Main main;

    private static Patient currentSelected;

    private static Map<Patient, Button> buttonMap;





    public void initializeManually() {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        c = new Client(serverAddress, serverPort, "Resident");
        c.setType("Resident");
        c.setVboxOfMessages(vBoxOfMessages);
        c.setObType((Object) this);
        System.out.println("Hioo");
        vBoxOfMessages.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollWindow.setVvalue((Double) newValue);
        });
    }


    @FXML
    void send(ActionEvent event) {
        String messageToSend = messageArea.getText();
        if(!messageToSend.isEmpty()){
           generateSendingMessage(messageToSend, vBoxOfMessages);


//Uncomment for same laptop
            if(currentSelected.getMyChat().isEmpty()){
                currentSelected.setMyChat(messageToSend+"~L");
            }
            else currentSelected.setMyChat(currentSelected.getMyChat() + "<"+messageToSend+"~L");
            c.sendMessage("Res"+"~"+currentSelected.getName()+"~"+messageToSend);
            messageArea.clear();
        }
    }

    public static void addLabel(String senderName, String messageFromOtherEnd, VBox vBox, Object p) {
        System.out.println(currentSelected.getName()+"~"+senderName+"~"+messageFromOtherEnd);
        //Uncomment for same laptop
        Patient ptn = (Patient)p;
        ptn.setMyChat(ptn.getMyChat()+"<"+messageFromOtherEnd+"~R");
        if(!currentSelected.getName().equals(senderName)){
            Button temp = buttonMap.get(p);
            temp.setStyle("-fx-background-color: none; -fx-font-weight: bold; -fx-border-width: 3px; -fx-border-radius: 40; -fx-border-color: black;");
            return;
        }

        VBox messageContainer = new VBox();
        messageContainer.setSpacing(1);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        Label nameLabel = new Label("Patient: " +ptn.getName());
        nameLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-padding: 0 0 0 5;");

        Text text = new Text(messageFromOtherEnd);
        text.setFill(Color.BLACK);

        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);
        messageContainer.getChildren().addAll(nameLabel, hBox);

       // vBox.getChildren().add(messageContainer);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(messageContainer);
            }
        });
    }

    void createVBoxOfMessages(Patient patient, VBox vBoxOfMessages) {
        if(Main.patientChatMap.containsChat(patient)) {
            patient.setMyChat(Main.patientChatMap.getChat(patient));
            String[] chats = patient.getMyChat().split("<");
            for(int i = 0; i < chats.length; i++) {
                String[] parts = chats[i].split("~");
                if(parts[1].equals("L")){
                    generateSendingMessage(parts[0], vBoxOfMessages);
                    //System.out.println("Done");
                }
                else if(parts[1].equals("R")){
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

        Label nameLabel = new Label("Patient: "+currentSelected.getName());
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
//                vBoxOfMessages.getChildren().add(messageContainer);
////                System.out.println(vBox);
//            }
//        });
    }


    public static  void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try{
            Main main = new Main();
            main.start(stage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollWindow.setStyle(
                "-fx-hbar-policy: never; " +
                        "-fx-vbar-policy: never;"
        );
        buttonMap = new HashMap<>();
        messagePane.setVisible(false);
        patientsBox.setSpacing(7);
       for(Patient p: Main.patientChatMap.chatMap.keySet()){
           HBox hBox = new HBox();
           hBox.setAlignment(Pos.CENTER_LEFT);
           Button button = new Button("Name: "+p.getName());
           button.setPrefWidth(286);
           button.setPrefHeight(43);
           button.setStyle("-fx-background-color: none; -fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 40");
           button.setText("ID: "+p.getId()+", Name: "+p.getName());
           button.setUserData(p);
           buttonMap.put(p, button);
           button.setOnAction(e -> {
               Patient temp = (Patient) ((Button)e.getSource()).getUserData();
               Button b = (Button) e.getSource();
//               b.setText("ID: "+temp.getId()+", Name: "+temp.getName());
               b.setStyle("-fx-background-color: none; -fx-font-weight: normal; -fx-border-width: 1px; -fx-border-radius: 40; -fx-border-color: black;");
               try {
                   //c.sendMessage("Res"+"~"+temp.getName()+"~"+"#Refresh");
                   //Uncomment for same laptop
                 PatientPageController.reloadPatientChats(temp);
                   main.loadPatientChats();
                   vBoxOfMessages.getChildren().clear();
                   //System.out.println(p.getMyChat());
               } catch (IOException  ex) {
                   throw new RuntimeException(ex);
               }
               currentSelected = temp;
              messagePane.setVisible(true);
              messageArea.clear();
              createVBoxOfMessages(currentSelected,vBoxOfMessages);
           });

           hBox.getChildren().add(button);
           patientsBox.getChildren().add(hBox);
       }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public void initManual() {

        patientsBox.getChildren().clear();
        for(Patient p: Main.patientChatMap.chatMap.keySet()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            Button button = new Button("Name: "+p.getName());
            button.setPrefWidth(286);
            button.setPrefHeight(43);
            button.setStyle("-fx-background-color: none; -fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 40");
            button.setText("ID: "+p.getId()+", Name: "+p.getName());
            button.setUserData(p);
            buttonMap.put(p, button);
            button.setOnAction(e -> {
                Patient temp = (Patient) ((Button)e.getSource()).getUserData();
                Button b = (Button) e.getSource();
                b.setStyle("-fx-background-color: none; -fx-font-weight: normal; -fx-border-width: 1px; -fx-border-radius: 40; -fx-border-color: black;");
                try {
                    //c.sendMessage("Res"+"~"+temp.getName()+"~"+"#Refresh");
                    //Uncomment for same laptop
                  PatientPageController.reloadPatientChats(temp);
                    //Thread.sleep(100);
                    main.loadPatientChats();
                    vBoxOfMessages.getChildren().clear();
                    //System.out.println(p.getMyChat());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                currentSelected = temp;
                messagePane.setVisible(true);
                messageArea.clear();
                createVBoxOfMessages(currentSelected,vBoxOfMessages);
            });
            hBox.getChildren().add(button);

            patientsBox.getChildren().add(hBox);
        }
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            if(currentSelected!=null) PatientPageController.reloadPatientChats(currentSelected);
            Stage stage = (Stage) patientsBox.getScene().getWindow();
            stage.close();
            main.showLoginPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logoutHover(MouseEvent mouseEvent) {
        logoutButton.setStyle("-fx-background-color: lightgray;");
    }

    public void logoutHoverExited(MouseEvent mouseEvent) {
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
}

//
//
//package com.example.hospital_management_system.doctor_page;
//
//import com.example.hospital_management_system.Main;
//import com.example.hospital_management_system.Networking.Client;
//import com.example.hospital_management_system.patient_page.Patient;
//import com.example.hospital_management_system.patient_page.PatientPageController;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextFlow;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import static javafx.application.Application.launch;
//
//public class ResidentPage extends Application implements Initializable {
//
//    public AnchorPane messagePane;
//    @FXML
//    private TextField messageArea;
//
//    @FXML
//    private VBox patientsBox;
//
//    @FXML
//    private ScrollPane scrollWindow;
//
//    @FXML
//    private Button sendButton;
//
//    @FXML
//    private VBox vBoxOfMessages;
//
//    private Client c;
//    private Main main;
//
//    private Patient currentSelected;
//
//
//
//
//    public void initializeManually() {
//        String serverAddress = "127.0.0.1";
//        int serverPort = 44444;
//        c = new Client(serverAddress, serverPort, "Resident");
//        c.setType("Resident");
//        c.setVboxOfMessages(vBoxOfMessages);
//        c.setObType((Object) this);
//        vBoxOfMessages.heightProperty().addListener((observable, oldValue, newValue) -> {
//            scrollWindow.setVvalue((Double) newValue);
//        });
//    }
//
//
//    @FXML
//    void send(ActionEvent event) {
//        String messageToSend = messageArea.getText();
//        if(!messageToSend.isEmpty()){
//            generateSendingMessage(messageToSend, vBoxOfMessages);
//
//            c.sendMessage("Res"+"~"+currentSelected.getName()+"~"+messageToSend);
//            messageArea.clear();
//        }
//    }
//
//    public static void addLabel(String senderName, String messageFromOtherEnd, VBox vBox) {
//        VBox messageContainer = new VBox();
//        messageContainer.setSpacing(1);
//
//        HBox hBox = new HBox();
//        hBox.setAlignment(Pos.CENTER_LEFT);
//        hBox.setPadding(new Insets(5, 5, 5, 5));
//
//        Label nameLabel = new Label("Patient" );
//        nameLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-padding: 0 0 0 5;");
//
//        Text text = new Text(messageFromOtherEnd);
//        text.setFill(Color.BLACK);
//
//        TextFlow textFlow = new TextFlow(text);
//        textFlow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 20px;");
//        textFlow.setPadding(new Insets(5, 10, 5, 10));
//
//        hBox.getChildren().add(textFlow);
//        messageContainer.getChildren().addAll(nameLabel, hBox);
//        // vBox.getChildren().add(messageContainer);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                vBox.getChildren().add(messageContainer);
//            }
//        });
//    }
//
//    void createVBoxOfMessages(Patient patient, VBox vBoxOfMessages) {
//        if(Main.patientChatMap.containsChat(patient)) {
//            patient.setMyChat(Main.patientChatMap.getChat(patient));
//            String[] chats = patient.getMyChat().split("<");
//            for(int i = 0; i < chats.length; i++) {
//                String[] parts = chats[i].split("~");
//                if(parts[1].equals("L")){
//                    generateSendingMessage(parts[0], vBoxOfMessages);
//                    //System.out.println("Done");
//                }
//                else if(parts[1].equals("R")){
//                    generateReceivingMessage(parts[0], vBoxOfMessages);
//                    //System.out.println("Done");
//                }
//            }
//
//        }
//        else{
//            System.out.println("No previous chat");
//        }
//
//    }
//
//    void generateSendingMessage(String message, VBox vBoxOfMessages) {
//        HBox hbox = new HBox();
//        hbox.setAlignment(Pos.CENTER_RIGHT);
//        hbox.setPadding(new Insets(5, 5, 5, 5));
//
//        Text text = new Text(message);
//        text.setFill(Color.color(0.934, 0.945, 0.996));
//        TextFlow textFlow = new TextFlow(text);
//
//        textFlow.setStyle("-fx-background-color: rgb(15, 125, 242);" +"-fx-background-radius: 20px;");
//        textFlow.setPadding(new Insets(5, 10, 5, 10));
//
//
//        hbox.getChildren().add(textFlow);
//        vBoxOfMessages.getChildren().add(hbox);
//    }
//
//    void generateReceivingMessage(String message, VBox vBoxOfMessages) {
//        VBox messageContainer = new VBox();
//        messageContainer.setSpacing(1);
//
//        HBox hBox = new HBox();
//        hBox.setAlignment(Pos.CENTER_LEFT);
//        hBox.setPadding(new Insets(5, 5, 5, 5));
//
//        Label nameLabel = new Label("Patient: "+currentSelected.getName());
//        nameLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-padding: 0 0 0 5;");
//
//        Text text = new Text(message);
//        text.setFill(Color.BLACK);
//
//        TextFlow textFlow = new TextFlow(text);
//        textFlow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 20px;");
//        textFlow.setPadding(new Insets(5, 10, 5, 10));
//
//        hBox.getChildren().add(textFlow);
//
//        messageContainer.getChildren().addAll(nameLabel, hBox);
//        vBoxOfMessages.getChildren().add(messageContainer);
////        Platform.runLater(new Runnable() {
////            @Override
////            public void run() {
////                vBoxOfMessages.getChildren().add(messageContainer);
//////                System.out.println(vBox);
////            }
////        });
//    }
//
//
//    public static  void main(String[] args){
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        try{
//            Main main = new Main();
//            main.start(stage);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public void init(){
//        messagePane.setVisible(false);
//        patientsBox.setSpacing(5);
//        for(Patient p: Main.patientChatMap.chatMap.keySet()){
//            HBox hBox = new HBox();
//            hBox.setAlignment(Pos.CENTER_LEFT);
//            Button button = new Button("Name: "+p.getName());
//            button.setPrefWidth(286);
//            button.setPrefHeight(43);
//            button.setStyle("-fx-background-color: none; -fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 40");
//            button.setUserData(p);
//            button.setOnAction(e -> {
//                Patient temp = (Patient) ((Button)e.getSource()).getUserData();
//                try {
//                    c.sendMessage("Res"+"~"+temp.getName()+"~"+"#Refresh");
//                    Thread.sleep(50);
//                    main.loadPatientChats();
//                    vBoxOfMessages.getChildren().clear();
//                    //System.out.println(p.getMyChat());
//                } catch (IOException | InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//                currentSelected = temp;
//                messagePane.setVisible(true);
//                messageArea.clear();
//                createVBoxOfMessages(currentSelected,vBoxOfMessages);
//            });
//            hBox.getChildren().add(button);
//            patientsBox.getChildren().add(hBox);
//        }
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        init();
//    }
//
//    public void setMain(Main main) {
//        this.main = main;
//    }
//
//    public  Main getMain(){
//        return main;
//    }
//}

