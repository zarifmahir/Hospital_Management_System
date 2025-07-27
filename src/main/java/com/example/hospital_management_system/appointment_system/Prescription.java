package com.example.hospital_management_system.appointment_system;

import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;

public class Prescription {
    private String diagnosis;
    private String date;
    private String patientName;
    private String doctorName;

    private Doctor doctor;
    private Patient patient;

    public Prescription(String diagnosis, Patient patient, Doctor doctor, String doctorName, String patientName, String date) {
        this.diagnosis = diagnosis;
        this.patient = patient;
        this.doctor = doctor;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
