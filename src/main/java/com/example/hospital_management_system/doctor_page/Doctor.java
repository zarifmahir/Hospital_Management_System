package com.example.hospital_management_system.doctor_page;

import com.example.hospital_management_system.Main;

import java.util.Random;

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



    public Doctor(String id, String name, int age, String gender, String bloodGroup, String email, String mobile, String emergencyContact, String medicalDegree, String institution, String pgQualification, int medicalLicense, String department, int yearsExperience, String medicalCouncil, String image) {

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
        this.id = id;
        this.shift = "";
        this.room = "";
        this.status = "";

        int roomNo = 0;
        Random random = new Random();

        if (department.equals("Medicine")) {
            roomNo = Main.roomNos[1]++;
        }
        else if (department.equals("Surgery")) {
            roomNo = Main.roomNos[2]++;
        }
        else if (department.equals("Pediatrics")) {
            roomNo = Main.roomNos[3]++;
        }
        else if (department.equals("Obstetrics")) {
            roomNo = Main.roomNos[4]++;
        }
        else if (department.equals("Gynecology")) {
            roomNo = Main.roomNos[5]++;
        }
        else if (department.equals("Orthopedics")) {
            roomNo = Main.roomNos[6]++;
        }
        else if (department.equals("Cardiology")) {
            roomNo = Main.roomNos[7]++;
        }
        else if (department.equals("Neurology")) {
            roomNo = Main.roomNos[8]++;
        }
        else if (department.equals("Pathology")) {
            roomNo = Main.roomNos[9]++;
        }
        else if (department.equals("Psychiatry")) {
            roomNo = Main.roomNos[10]++;
        }
        else if (department.equals("Dermatology")) {
            roomNo = Main.roomNos[11]++;
        }

        this.room = String.valueOf(roomNo);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setImage(String image) {
        this.image = image;
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
