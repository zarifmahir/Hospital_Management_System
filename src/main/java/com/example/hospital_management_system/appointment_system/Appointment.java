package com.example.hospital_management_system.appointment_system;

import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;

import java.util.Date;

public class Appointment {
    String doctorName;
    String patientName;
    String date;

    int id;
    String time;

    public Appointment(String doctorName, String patientName, String date, int id, String time) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
        this.id = id;
        this.time = time;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
