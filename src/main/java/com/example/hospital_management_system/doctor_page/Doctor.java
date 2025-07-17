package com.example.hospital_management_system.doctor_page;

public class Doctor {
    private String name;
    private int age;
    private String gender;
    private String bloodGroup;
    private String email;
    private String mobile;
    private String emergencyContact;
    private String medicalDegree;
    private String institution;
    private String pgQualification;

    private int medicalLicense;
    private String department;
    private int yearsExperience;
    private String medicalCouncil;
    private String image;

    private String userName;
    private String pass;

    private String id;
    private String shift;
    private String room;
    private String status;



    public Doctor(String name, int age, String gender, String bloodGroup, String email, String mobile, String emergencyContact, String medicalDegree, String institution, String pgQualification, int medicalLicense, String department, int yearsExperience, String medicalCouncil, String image) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.mobile = mobile;
        this.emergencyContact = emergencyContact;
        this.medicalDegree = medicalDegree;
        this.institution = institution;
        this.pgQualification = pgQualification;
        this.medicalLicense = medicalLicense;
        this.department = department;
        this.yearsExperience = yearsExperience;
        this.medicalCouncil = medicalCouncil;
        this.image = image;
        this.id = "";
        this.shift = "";
        this.room = "";
        this.status = "";
    }

    public void setUserNameAndPass(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getShift() {
        return shift;
    }

    public String getRoom() {
        return room;
    }

    public String getStatus() {
        return status;
    }

    public String getDepartment() {
        return department;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getMedicalDegree() {
        return medicalDegree;
    }

    public String getInstitution() {
        return institution;
    }

    public String getPgQualification() {
        return pgQualification;
    }

    public int getMedicalLicense() {
        return medicalLicense;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public String getMedicalCouncil() {
        return medicalCouncil;
    }

    public String getImage() {
        return image;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
