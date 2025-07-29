package com.example.hospital_management_system;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the username and password fields
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font("Arial", 18));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font("Arial", 18));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        // Create the login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Create the layout and add elements
        VBox loginForm = new VBox(10);
        loginForm.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        loginForm.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f4; -fx-alignment: center;");

        // Create the animated background (a timeline that changes the background color)
        StackPane root = new StackPane();

        // Create the text that says "EPUET HOSPITAL"
        Text hospitalText = new Text("EPUET HOSPITAL");
        hospitalText.setFont(new Font("Arial", 30));
        hospitalText.setFill(Color.rgb(200, 206, 238)); // #c8ceee color
        hospitalText.setOpacity(0);

        // Create a timeline for the sliding text animation
        Timeline textAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(hospitalText.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(hospitalText.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(5), new KeyValue(hospitalText.translateXProperty(), 600))
        );

        // Create a timeline for the animated background color
        Timeline backgroundAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(root.styleProperty(), "-fx-background-color: #c8ceee;")),
                new KeyFrame(Duration.seconds(4), new KeyValue(root.styleProperty(), "-fx-background-color: #a2b5d4;")),
                new KeyFrame(Duration.seconds(8), new KeyValue(root.styleProperty(), "-fx-background-color: #c8ceee;"))
        );

        // Play both animations
        backgroundAnimation.setCycleCount(Timeline.INDEFINITE);
        textAnimation.setCycleCount(1);
        backgroundAnimation.play();
        textAnimation.play();

        // Initially position the form off the screen
        loginForm.setTranslateY(500);

        // Create TranslateTransition for form sliding up
        Timeline formSlideUp = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(loginForm.translateYProperty(), 500)),
                new KeyFrame(Duration.seconds(1), new KeyValue(loginForm.translateYProperty(), 0))
        );

        // Play the slide-up animation
        formSlideUp.play();

        // Add all elements to the root
        root.getChildren().addAll(hospitalText, loginForm);

        // Set the scene
        Scene scene = new Scene(root, 700, 300, Color.WHITE);
        primaryStage.setTitle("Hospital Management System - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
