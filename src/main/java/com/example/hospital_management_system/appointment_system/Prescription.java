package com.example.hospital_management_system.appointment_system;

import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;

import java.io.*;

public class Prescription {
    private static int id = getLatestPrescriptionId();

    public static int getLatestPrescriptionId() {
        try (BufferedReader br = new BufferedReader(new FileReader("texts/PrescriptionIds.txt"))) {
            String line;
            line = br.readLine();
            while (line != null) {
                int id = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("texts/PrescriptionIds.txt"))) {
            bw.write(Integer.toString(id + 1));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return id + 1;
    }

    private String myId;

    private String diagnosis;
    private String date;
    private String patientName;
    private String doctorId;
    private String doctorName;
    private String patientId;

    private Doctor doctor;
    private Patient patient;


    public Prescription(String diagnosis, Patient patient, Doctor doctor, String doctorName, String doctorId, String patientName, String patientId, String date) {
        this.diagnosis = diagnosis;
        this.patient = patient;
        this.doctor = doctor;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.date = date;

        myId = String.valueOf(id);
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
