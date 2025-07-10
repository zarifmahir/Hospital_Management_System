package com.example.hospital_management_system.patient_page;

import javafx.scene.control.CheckBox;

import java.awt.*;

public class Patient {
    private String name;
    private int age;
    private String gender;
    private float weight;
    private float height;
    private String bloodType;

    private int mobile;
    private int emergencyContact;
    private String email;


    private CheckBox diabetes;
    private CheckBox asthma;
    private CheckBox highBp;
    private CheckBox epilepsy;
    private CheckBox cancer;
    private CheckBox stroke;
    private CheckBox kidney;
    private CheckBox liver;
    private CheckBox allergies;
    private CheckBox surgeries;
    private CheckBox familyHistory;
    private CheckBox medications;

    private String id;
    private String pass;

    public Patient(String name, int age, String gender, float weight, float height, String bloodType, int mobile, int emergencyContact, String email, CheckBox medications, CheckBox familyHistory, CheckBox surgeries, CheckBox allergies, CheckBox liver, CheckBox kidney, CheckBox stroke, CheckBox cancer, CheckBox epilepsy, CheckBox highBp, CheckBox asthma, CheckBox diabetes) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.diabetes = diabetes;
        this.weight = weight;
        this.height = height;
        this.bloodType = bloodType;
        this.mobile = mobile;
        this.emergencyContact = emergencyContact;
        this.email = email;
        this.medications = medications;
        this.familyHistory = familyHistory;
        this.surgeries = surgeries;
        this.allergies = allergies;
        this.liver = liver;
        this.kidney = kidney;
        this.stroke = stroke;
        this.cancer = cancer;
        this.epilepsy = epilepsy;
        this.highBp = highBp;
        this.asthma = asthma;
    }

    

    public void setPassAndId(String pass, String id){
        this.pass = pass;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getMobile() {
        return mobile;
    }

    public int getEmergencyContact() {
        return emergencyContact;
    }

    public String getEmail() {
        return email;
    }

    public CheckBox getDiabetes() {
        return diabetes;
    }

    public CheckBox getAsthma() {
        return asthma;
    }

    public CheckBox getHighBp() {
        return highBp;
    }

    public CheckBox getEpilepsy() {
        return epilepsy;
    }

    public CheckBox getCancer() {
        return cancer;
    }

    public CheckBox getStroke() {
        return stroke;
    }

    public CheckBox getKidney() {
        return kidney;
    }

    public CheckBox getLiver() {
        return liver;
    }

    public CheckBox getAllergies() {
        return allergies;
    }

    public CheckBox getSurgeries() {
        return surgeries;
    }

    public CheckBox getFamilyHistory() {
        return familyHistory;
    }

    public CheckBox getMedications() {
        return medications;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
}
