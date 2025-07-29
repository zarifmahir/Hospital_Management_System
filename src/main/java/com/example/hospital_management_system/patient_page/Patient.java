package com.example.hospital_management_system.patient_page;

import com.example.hospital_management_system.appointment_system.Prescription;

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


    private boolean diabetes;
    private boolean asthma;
    private boolean highBp;
    private boolean epilepsy;
    private boolean cancer;
    private boolean stroke;
    private boolean kidney;
    private boolean liver;
    private boolean allergies;
    private boolean surgeries;
    private boolean familyHistory;
    private boolean medications;

    private String username;
    private String pass;
    private String image;

    private String id;
    private String myChat;

    private String primaryDiagnosis = "";
    private int [] prescriptionIds;

    private int testReportNumbers;


    public Patient(String id, String name, int age, String gender, float weight, float height, String bloodType, int mobile, int emergencyContact, String email, boolean diabetes, boolean asthma, boolean highBp, boolean epilepsy, boolean cancer, boolean stroke, boolean kidney, boolean liver, boolean allergies, boolean surgeries, boolean familyHistory, boolean medications, String image) {
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
        this.image = image;
        this.myChat = "";
        this.id = id;
        testReportNumbers = 0;
    }

    public int getTestReportNumbers() {
        return testReportNumbers;
    }

    public void setTestReportNumbers(int testReportNumbers) {
        this.testReportNumbers = testReportNumbers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setEmergencyContact(int emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMyChat() {
        return myChat;
    }

    public void setMyChat(String myChat) {
        this.myChat = myChat;
    }

    public String getImage() {
        return image;
    }

    public void setPassAndId(String pass, String username){
        this.pass = pass;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobile() {
        return String.valueOf(mobile);
    }

    public int getEmergencyContact() {
        return emergencyContact;
    }

    public String getEmail() {
        return email;
    }

    public boolean getDiabetes() {
        return diabetes;
    }

    public boolean getAsthma() {
        return asthma;
    }

    public boolean getHighBp() {
        return highBp;
    }

    public boolean getEpilepsy() {
        return epilepsy;
    }

    public boolean getCancer() {
        return cancer;
    }

    public boolean getStroke() {
        return stroke;
    }

    public boolean getKidney() {
        return kidney;
    }

    public boolean getLiver() {
        return liver;
    }

    public boolean getAllergies() {
        return allergies;
    }

    public boolean getSurgeries() {
        return surgeries;
    }

    public boolean getFamilyHistory() {
        return familyHistory;
    }

    public boolean getMedications() {
        return medications;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }
}
