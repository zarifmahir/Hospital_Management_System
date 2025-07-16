module com.example.hospital_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.hospital_management_system to javafx.fxml;
    exports com.example.hospital_management_system;
    exports com.example.hospital_management_system.patient_page;
    opens com.example.hospital_management_system.patient_page to javafx.fxml;
    exports com.example.hospital_management_system.register_page;
    opens com.example.hospital_management_system.register_page to javafx.fxml;
    opens com.example.hospital_management_system.doctor_page to javafx.fxml;
    exports com.example.hospital_management_system.doctor_page;
    opens com.example.hospital_management_system.admin_page to javafx.fxml;
    exports com.example.hospital_management_system.admin_page;
}