package com.example.hospital_management_system.Staff;

abstract public class Staff {
    String name;
    String department;
    String yearsOfExperience;
    String phoneNumber;
    String email;
    String address;
    String role;


    public Staff(String name, String department, String yearsOfExperience, String phoneNumber, String email, String address) {
        this.name = name;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
