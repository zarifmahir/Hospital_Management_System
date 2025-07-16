package com.example.hospital_management_system.appointment_system;

import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;

import java.util.Date;

public class Appointment {
    Patient patient;
    Doctor doctor;
    Date date;

    int id;

    Appointment(Date date, Patient patient, Doctor doctor) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public Boolean has(Patient patient) {
        return this.patient.equals(patient);
    }

    public Boolean has(Doctor doctor) {
        return this.doctor.equals(doctor);
    }

}
