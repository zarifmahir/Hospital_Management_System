package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.appointment_system.Appointment;
import com.example.hospital_management_system.appointment_system.AppointmentMap;
import com.example.hospital_management_system.appointment_system.Prescription;
import com.example.hospital_management_system.patient_page.Patient;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
        numReportField.setText(String.valueOf(Main.prescriptionMap.size()));
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

        XYChart.Series<String, Integer> seriesPatientAge = new XYChart.Series<>();
//        XYChart.Series<String, Integer> seriesPatientGender = new XYChart.Series<>();

        List<Patient> patientList = Main.patientsMap.getPatientList();

        int maleCount = 0;
        int femaleCount = 0;

        int underTen = 0;
        int tenToTwenty = 0;
        int twentyToThirty = 0;
        int thirtyToFifty = 0;
        int fiftyToSeventy = 0;
        int overSeventy = 0;

        for (Patient p : patientList) {
            if (p.getGender().equals("Male")) {
                maleCount++;
            }
            if (p.getGender().equals("Female")) {
                femaleCount++;
            }

            if (p.getAge() < 10) {
                underTen++;
            }
            else if (p.getAge() < 20) {
                tenToTwenty++;
            }
            else if (p.getAge() < 30) {
                twentyToThirty++;
            }
            else if (p.getAge() < 50) {
                thirtyToFifty++;
            }
            else if (p.getAge() < 70) {
                fiftyToSeventy++;
            }
            else if (p.getAge() < 80) {
                overSeventy++;
            }
        }

//        seriesPatientGender.getData().add(new XYChart.Data<>("Male", maleCount));
//        seriesPatientGender.getData().add(new XYChart.Data<>("Female", femaleCount));

        seriesPatientAge.getData().add(new XYChart.Data<>("<10", underTen));
        seriesPatientAge.getData().add(new XYChart.Data<>("20-30", tenToTwenty));
        seriesPatientAge.getData().add(new XYChart.Data<>("30-40", twentyToThirty));
        seriesPatientAge.getData().add(new XYChart.Data<>("40-50", thirtyToFifty));
        seriesPatientAge.getData().add(new XYChart.Data<>("50-60", fiftyToSeventy));
        seriesPatientAge.getData().add(new XYChart.Data<>("60-70", overSeventy));

        patientAgeBarGraph.getData().add(seriesPatientAge);
        patientAgeBarGraph.setLegendVisible(false);

        float malePercent = (float) maleCount / (float) (maleCount + femaleCount);
        float femalePercent = 100 - malePercent;

        ObservableList<PieChart.Data> patientGenderPieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Male", malePercent),
                new PieChart.Data("Female", femalePercent)
        );

        patientGenderPieChart.setData(patientGenderPieChartData);

//        patientGenderBarGraph.getData().add(seriesPatientGender);
//        patientGenderBarGraph.setLegendVisible(false);

//        SET COLOR FOR BARGRAPH
        String[] colors = { "#1f77b4" , "#ff7f0e" , "#2ca02c" };
        for (int i = 0; i < seriesStaff.getData().size(); i++) {
            XYChart.Data<String, Integer> data = seriesStaff.getData().get(i);
            data.getNode().setStyle("-fx-bar-fill: " + colors[i % colors.length] + ";");
        }

        for (int i = 0; i < seriesDoctor.getData().size(); i++) {
            XYChart.Data<String, Integer> data = seriesDoctor.getData().get(i);
            data.getNode().setStyle("-fx-bar-fill: " + colors[i % colors.length] + ";");
        }

        for (int i = 0; i < seriesPatientAge.getData().size(); i++) {
            XYChart.Data<String, Integer> data = seriesPatientAge.getData().get(i);
            data.getNode().setStyle("-fx-bar-fill: " + colors[i % colors.length] + ";");
        }



        //SETTING THE LINECHART FOR APPOINTMENT AND PRESCRIPTIONS
        XYChart.Series<String, Integer> seriesAppointment = new XYChart.Series<>();
        XYChart.Series<String, Integer> seriesPrescription = new XYChart.Series<>();

        List<Appointment> appointments = Main.appointmentMap.getAppointments();
        List<Prescription> prescriptions = Main.prescriptionMap.getPrescriptionList();

        int [] appointmentCounts = new int[12];
        int [] prescriptionCounts = new int[12];

        for (Appointment ap : appointments) {
            String [] date = ap.getDate().split("-");
            int month = Integer.parseInt(date[1]);

            appointmentCounts[month - 1]++;
        }

        for (Prescription p : prescriptions) {
            String [] date = p.getDate().split("-");
            int month = Integer.parseInt(date[1]);
            prescriptionCounts[month - 1]++;
        }

        seriesAppointment.getData().add(new XYChart.Data<>("January", appointmentCounts[0]));
        seriesAppointment.getData().add(new XYChart.Data<>("February", appointmentCounts[1]));
        seriesAppointment.getData().add(new XYChart.Data<>("March", appointmentCounts[2]));
        seriesAppointment.getData().add(new XYChart.Data<>("April", appointmentCounts[3]));
        seriesAppointment.getData().add(new XYChart.Data<>("May", appointmentCounts[4]));
        seriesAppointment.getData().add(new XYChart.Data<>("June", appointmentCounts[5]));
        seriesAppointment.getData().add(new XYChart.Data<>("July", appointmentCounts[6]));
        seriesAppointment.getData().add(new XYChart.Data<>("August", appointmentCounts[7]));
        seriesAppointment.getData().add(new XYChart.Data<>("September", appointmentCounts[8]));
        seriesAppointment.getData().add(new XYChart.Data<>("October", appointmentCounts[9]));
        seriesAppointment.getData().add(new XYChart.Data<>("November", appointmentCounts[10]));
        seriesAppointment.getData().add(new XYChart.Data<>("December", appointmentCounts[11]));

        seriesPrescription.getData().add(new XYChart.Data<>("January", prescriptionCounts[0]));
        seriesPrescription.getData().add(new XYChart.Data<>("February", prescriptionCounts[1]));
        seriesPrescription.getData().add(new XYChart.Data<>("March", prescriptionCounts[2]));
        seriesPrescription.getData().add(new XYChart.Data<>("April", prescriptionCounts[3]));
        seriesPrescription.getData().add(new XYChart.Data<>("May", prescriptionCounts[4]));
        seriesPrescription.getData().add(new XYChart.Data<>("June", prescriptionCounts[5]));
        seriesPrescription.getData().add(new XYChart.Data<>("July", prescriptionCounts[6]));
        seriesPrescription.getData().add(new XYChart.Data<>("August", prescriptionCounts[7]));
        seriesPrescription.getData().add(new XYChart.Data<>("September", prescriptionCounts[8]));
        seriesPrescription.getData().add(new XYChart.Data<>("October", prescriptionCounts[9]));
        seriesPrescription.getData().add(new XYChart.Data<>("November", prescriptionCounts[10]));
        seriesPrescription.getData().add(new XYChart.Data<>("December", prescriptionCounts[11]));

        apLineChart.getData().addAll(seriesAppointment, seriesPrescription);
        apLineChart.setLegendVisible(false);
    }

    @FXML
    private BarChart<String, Integer> patientAgeBarGraph;

//    @FXML
//    private BarChart<String, Integer> patientGenderBarGraph;

    @FXML
    private PieChart patientGenderPieChart;

    @FXML
    private BarChart<String, Integer> staffBarGraph;

    @FXML
    private LineChart<String, Integer> apLineChart;




    @FXML
    private AnchorPane PrescriptionAp;
    @FXML
    private AnchorPane appointmentAp;
    @FXML
    private AnchorPane doctorAp;
    @FXML
    private AnchorPane patientAp;
    @FXML
    private AnchorPane staffAp;


    @FXML
    void appointmentHover(MouseEvent event) {
        appointmentAp.setStyle("-fx-background-color: lightgray; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void appointmentHoverExited(MouseEvent event) {
        appointmentAp.setStyle("-fx-background-color: lightblue; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void appointmentPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), appointmentAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void doctorLabelHover(MouseEvent event) {
        doctorAp.setStyle("-fx-background-color: lightgray; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void doctorLabelHoverExited(MouseEvent event) {
        doctorAp.setStyle("-fx-background-color: lightblue; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void doctorLabelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), doctorAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void patientHover(MouseEvent event) {
        patientAp.setStyle("-fx-background-color: lightgray; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void patientHoverExited(MouseEvent event) {
        patientAp.setStyle("-fx-background-color:  #c9a5c7; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void patientLabelPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), patientAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void reportHover(MouseEvent event) {
        PrescriptionAp.setStyle("-fx-background-color: lightgray; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void reportHoverExited(MouseEvent event) {
        PrescriptionAp.setStyle("-fx-background-color:  #c3dedd; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void reportPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), PrescriptionAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
    }

    @FXML
    void staffHover(MouseEvent event) {
        staffAp.setStyle("-fx-background-color: lightgray; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void staffHoverExited(MouseEvent event) {
        staffAp.setStyle("-fx-background-color: #cbd3ad; -fx-background-radius: 30 30 30 30;");
    }

    @FXML
    void staffPressed(MouseEvent event) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), staffAp);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);
        scaleDown.play();
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
    void showReportPage(MouseEvent event) throws IOException {
        adminPageController.loadPage("show_all_prescriptions");
    }

    @FXML
    void showStaffPanel(MouseEvent event) throws IOException {
        adminPageController.loadPage("staff_page");
    }


    @FXML
    void showAppointmentPage(MouseEvent event) throws IOException {
        adminPageController.loadPage("appointment_page");
    }

}
