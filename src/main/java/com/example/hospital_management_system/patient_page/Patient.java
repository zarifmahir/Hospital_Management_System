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


}
