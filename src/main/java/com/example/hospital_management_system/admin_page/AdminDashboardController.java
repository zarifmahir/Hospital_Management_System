package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.AppointmentMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.example.hospital_management_system.Main.staffMap;

public class AdminDashboardController {
    @FXML
    private AnchorPane apTop;

    @FXML
    private Label designationField;

    @FXML
    private Label idField;

    @FXML
    private Label nameField;

    @FXML
    private Circle pfpCircle;

    @FXML
    private Label roomField;

    public AdminPageController adminPageController;


    public Admin admin;
    public void setAdmin(Admin admin) {
        this.admin = admin;
        designationField.setText(admin.getDesignation());
        nameField.setText(admin.getName());
        idField.setText(admin.getUser());
        roomField.setText(admin.getRoom_number());

        String img = "/images/user.png";
        if(!admin.getImage().equals("null")){ img = admin.getImage();}
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(img)));
//        doctorPhoto.setImage(image);
        pfpCircle.setFill(new ImagePattern(image));


        numAppointmentField.setText(String.valueOf(Main.appointmentMap.size()));
        numDoctorField.setText(String.valueOf(Main.doctorsMap.size()));
        numPatientField.setText(String.valueOf(Main.patientsMap.size()));
//      numReportField.setText(Re)
        numStaffField.setText(String.valueOf(staffMap.size()));
    }


    @FXML
    private Label numAppointmentField;


    @FXML
    private Label numDoctorField;

    @FXML
    private Label numPatientField;

    @FXML
    private Label numReportField;

    @FXML
    private Label numStaffField;

    //GRAPH RELATED VARIABLES AND FUNCTIONS

    @FXML
    private PieChart appointmentPieChar;

    @FXML
    private BarChart<String, Integer> doctorBarGraph;

    public void initialize() {
        //doctor bar graph
        XYChart.Series<String, Integer> seriesDoctor = new XYChart.Series<>();

        List<String> departments = Main.doctorsMap.getDepartments();

        for (String department : departments) {
            seriesDoctor.getData().add(new XYChart.Data<>(department, Main.doctorsMap.getDepartmentWiseDoctors(department).size()));
        }

        doctorBarGraph.getData().add(seriesDoctor);
        doctorBarGraph.setLegendVisible(false);


        //staff bar graph
        XYChart.Series<String, Integer> seriesStaff = new XYChart.Series<>();

        seriesStaff.getData().add(new XYChart.Data<>("Nurses", Main.staffMap.nurseCount()));
        seriesStaff.getData().add(new XYChart.Data<>("Receptionists", Main.staffMap.receptionistCount()));
        seriesStaff.getData().add(new XYChart.Data<>("Ward Boys", Main.staffMap.bedBoyCount()));

        staffBarGraph.getData().add(seriesStaff);
        staffBarGraph.setLegendVisible(false);


        //SET COLOR FOR BARGRAPH
//        for (int i = 0; i < seriesStaff.getData().size(); i++) {
//            XYChart.Data<String, Integer> data = seriesStaff.getData().get(i);
//            String[] colors = {"#1f77b4", "#ff7f0e", "#2ca02c"};
//            data.getNode().setStyle("-fx-bar-fill: " + colors[i % colors.length] + ";");
//        }
//
//        for (int i = 0; i < seriesDoctor.getData().size(); i++) {
//            XYChart.Data<String, Integer> data = seriesDoctor.getData().get(i);
//            String[] colors = {"#1f77b4", "#ff7f0e", "#2ca02c"};
//            data.getNode().setStyle("-fx-bar-fill: " + colors[i % colors.length] + ";");
//        }

    }

    @FXML
    private AnchorPane patientAgeBarGraph;

    @FXML
    private BarChart<?, ?> patientGenderBarGraph;

    @FXML
    private PieChart reportPieChart;

    @FXML
    private BarChart<String, Integer> staffBarGraph;


    @FXML
    void appointmentHover(MouseEvent event) {

    }

    @FXML
    void appointmentHoverExited(MouseEvent event) {

    }

    @FXML
    void appointmentPressed(MouseEvent event) {

    }

    @FXML
    void doctorLabelHover(MouseEvent event) {

    }

    @FXML
    void doctorLabelHoverExited(MouseEvent event) {

    }

    @FXML
    void patientHover(MouseEvent event) {

    }

    @FXML
    void patientHoverExited(MouseEvent event) {

    }

    @FXML
    void reportHover(MouseEvent event) {

    }

    @FXML
    void reportHoverExited(MouseEvent event) {

    }

    @FXML
    void reportPressed(MouseEvent event) {

    }

    @FXML
    void showAppointmentPage(MouseEvent event) {

    }

    @FXML
    void showDoctorPanel(MouseEvent event) throws IOException {
        adminPageController.loadPage("doctors_panel");
    }

    @FXML
    void showPatientPanel(MouseEvent event) throws IOException {
        adminPageController.loadPage("patients_panel");
    }

    @FXML
    void showReportPage(MouseEvent event) {

    }

    @FXML
    void showStaffPanel(MouseEvent event) throws IOException {
        adminPageController.loadPage("staff_page");
    }

    @FXML
    void staffHover(MouseEvent event) {

    }

    @FXML
    void staffHoverExited(MouseEvent event) {

    }

    @FXML
    void staffPressed(MouseEvent event) {

    }



    @FXML
    void showAppointments(ActionEvent event) {

    }



}
