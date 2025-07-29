package com.example.hospital_management_system.appointment_system;

import com.example.hospital_management_system.doctor_page.Doctor;
import com.example.hospital_management_system.patient_page.Patient;

import java.io.*;

public class Prescription {
    public static int getLatestPrescriptionId() {
        int x = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/texts/PrescriptionIds.txt"))) {
            String line;
            line = br.readLine();
            while (line != null) {
                x = Integer.parseInt(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/texts/PrescriptionIds.txt", true))) {
            bw.write(Integer.toString(x + 1));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return x + 1;
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

    private String remedy1;
    private String remedy2;
    private String remedy3;
    private String remedy4;
    private String remedy5;
    private String remedy6;


    public Prescription(String diagnosis, String doctorName, String doctorId, String patientName, String patientId, String date) {
        this.diagnosis = diagnosis;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.date = date;

        myId = String.valueOf(getLatestPrescriptionId());
    }

    public Prescription(String myId, String diagnosis, String doctorName, String doctorId, String patientName, String patientId, String date) {
        this.diagnosis = diagnosis;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.date = date;

        this.myId = myId;
    }

    public void setRemedy1(String remedy1) {
        this.remedy1 = remedy1;
    }

    public void setRemedy2(String remedy2) {
        this.remedy2 = remedy2;
    }

    public void setRemedy3(String remedy3) {
        this.remedy3 = remedy3;
    }

    public void setRemedy4(String remedy4) {
        this.remedy4 = remedy4;
    }

    public void setRemedy5(String remedy5) {
        this.remedy5 = remedy5;
    }

    public void setRemedy6(String remedy6) {
        this.remedy6 = remedy6;
    }

    public String getRemedy1() {
        return remedy1;
    }

    public String getRemedy2() {
        return remedy2;
    }

    public String getRemedy3() {
        return remedy3;
    }

    public String getRemedy4() {
        return remedy4;
    }

    public String getRemedy5() {
        return remedy5;
    }

    public String getRemedy6() {
        return remedy6;
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
