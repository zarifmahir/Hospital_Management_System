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
    private String specialization;
    private int yearsExperience;
    private String medicalCouncil;
    private String image;

    private String id;
    private String pass;

    public Doctor(String name, int age, String gender, String bloodGroup, String email, String mobile, String emergencyContact, String medicalDegree, String institution, String pgQualification, int medicalLicense, String specialization, int yearsExperience, String medicalCouncil, String image) {

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
        this.specialization = specialization;
        this.yearsExperience = yearsExperience;
        this.medicalCouncil = medicalCouncil;
        this.image = image;
    }

    public void setIdAndPass(String id, String pass) {
        this.id = id;
        this.pass = pass;
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

    public String getSpecialization() {
        return specialization;
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

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
}
