package com.example.hospital_management_system;

import com.example.hospital_management_system.admin_page.Admin;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML
    private ImageView logoImage;

    @FXML
    public ChoiceBox<String> dropdown;
    public Button signUpButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button LogInButton;

    @FXML
    private Label errorMessage;

    @FXML
    private Label hospitalName;

    private Main main;



    private void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Doctor", "Patient", "Admin");
        dropdown.setItems(list);
        errorMessage.setVisible(false);

        TranslateTransition nameSlide = new TranslateTransition(Duration.seconds(1), hospitalName);
        nameSlide.setFromX(-300);
        nameSlide.setToX(0);

        TranslateTransition logoSlide = new TranslateTransition(Duration.seconds(1), logoImage);
        logoSlide.setFromX(250);
        logoSlide.setToX(0);

        SequentialTransition sequentialTransition = new SequentialTransition(
                nameSlide,
                logoSlide
        );
        sequentialTransition.play();

        TranslateTransition loginSlide = new TranslateTransition(Duration.seconds(1), LogInButton);
        loginSlide.setFromY(100);
        loginSlide.setToY(0);

        FadeTransition dropdownFade = new FadeTransition(Duration.seconds(3.5), dropdown);
        dropdownFade.setFromValue(0);
        dropdownFade.setToValue(1);

        FadeTransition usernameFade = new FadeTransition(Duration.seconds(3.5), username);
        usernameFade.setFromValue(0);
        usernameFade.setToValue(1);

        FadeTransition passwordFade = new FadeTransition(Duration.seconds(3.5), password);
        passwordFade.setFromValue(0);
        passwordFade.setToValue(1);

        FadeTransition logInFade = new FadeTransition(Duration.seconds(3.5), LogInButton);
        logInFade.setFromValue(0);
        logInFade.setToValue(1);

        FadeTransition signUpFade = new FadeTransition(Duration.seconds(3.5), signUpButton);
        signUpFade.setFromValue(0);
        signUpFade.setToValue(1);

        ParallelTransition parallelTransition = new ParallelTransition(
                dropdownFade,
                usernameFade,
                passwordFade,
                logInFade,
                signUpFade
        );

        parallelTransition.play();


//        TranslateTransition logoSlide = new TranslateTransition(Duration.seconds(1.5), logoImage);
//        logoSlide.setFromY(-100);
//        logoSlide.setToY(0);
//
//        TranslateTransition usernameSlide = new TranslateTransition(Duration.seconds(1.5), username);
//        usernameSlide.setFromX(-300);
//        usernameSlide.setToX(0);
//
//        TranslateTransition passwordSlide = new TranslateTransition(Duration.seconds(1.5), password);
//        passwordSlide.setFromX(300);
//        passwordSlide.setToX(0);
//
//        TranslateTransition loginSlide = new TranslateTransition(Duration.seconds(1.5), LogInButton);
//        loginSlide.setFromY(100);
//        loginSlide.setToY(0);
//
//        FadeTransition signUpFade = new FadeTransition(Duration.seconds(1), signUpButton);
//        signUpFade.setFromValue(0);
//        signUpFade.setToValue(1);
//
//        SequentialTransition seqTransition = new SequentialTransition(
//                logoSlide
//        );
//
//        seqTransition.play();
//
//        ParallelTransition parallelTransition = new ParallelTransition(
//                usernameSlide,
//                passwordSlide
//        );
//        parallelTransition.play();
//
//        parallelTransition = new ParallelTransition(
//                loginSlide,
//                signUpFade
//        );
//        parallelTransition.play();
    }

    public void userLogIn(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {


        String personType = dropdown.getValue();

        if(dropdown.getValue()== null || username.getText().isEmpty() || password.getText().isEmpty()){
            errorMessage.setVisible(true);
            errorMessage.setText("Please fill all the fields");
        }
        else {
            try{
                if(personType.equals("Doctor")) {
                    String userAndPass = username.getText() + "@" + password.getText();
                    System.out.println(userAndPass);
                    if(Main.doctorsMap.searchDoctor(userAndPass)){
                        main.showDoctorPage(Main.doctorsMap.getDoctor(userAndPass));
                    }
                    else{
                        errorMessage.setVisible(true);
                        errorMessage.setText("Invalid username or password");
                    }
                }
                else if (personType.equals("Patient")) {
                    String userAndPass = username.getText() + "@" + password.getText();
                    System.out.println(userAndPass);
                    if(Main.patientsMap.searchPatient(userAndPass)){
                        main.showPatientPage(Main.patientsMap.getPatient(userAndPass));
                    }
                    else{
                        errorMessage.setVisible(true);
                        errorMessage.setText("Invalid username or password");
                    }
                }
                else if (personType.equals("Admin")) {
                        String userAndPass = username.getText() + "@" + password.getText();
                        System.out.println(userAndPass);
                        if(username.getText().equals("b") && password.getText().equals("222")){
                            main.showResidentPage();
                        }
                        else if (Main.adminMap.searchAdmin(userAndPass)){
                            Admin a = Main.adminMap.getAdmin(userAndPass);
                            System.out.println(a.getName());
                            main.showAdminPage(a);
                        }
                        else {
                            errorMessage.setVisible(true);
                            errorMessage.setText("Invalid username or password");
                        }
                    }
//                    if(username.getText().equals("b") && password.getText().equals("111")){
//                        main.showResidentPage();
//                    }
                }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setMain(Main main) {
        initialize();
        this.main = main;
    }

    public void signUp(ActionEvent actionEvent) {
        try {
            main.showRegisterPage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void keyEntered(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            checkLogin();
        }
    }
}
