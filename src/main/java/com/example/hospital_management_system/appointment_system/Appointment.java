package com.example.hospital_management_system.appointment_system;

import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;

import java.util.Date;
import java.util.Random;

public class Appointment {
    String doctorName;
    String patientName;
    String patientId;
    String date;

    int id;
    String time;
    String department;
    int roomNo;

    public Appointment(String doctorName, String patientName, String date, int id, String time, String department, int roomNo, String patientId) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
        this.id = id;
        this.time = time;
        this.department = department;
        this.roomNo = roomNo;
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
