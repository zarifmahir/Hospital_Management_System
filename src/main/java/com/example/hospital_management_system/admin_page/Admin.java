package com.example.hospital_management_system.admin_page;

public class Admin {
    private String name;
    private String user;
    private String password;
    private String designation;
    private String room_number;
    private String image = "null";

    public Admin(String name, String user, String password, String designation, String room_number, String image) {
        this.user = user;
        this.name = name;
        this.password = password;
        this.designation = designation;
        this.room_number = room_number;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("User: " + user);
        System.out.println("Password: " + password);
        System.out.println("Designation: " + designation);
        System.out.println("Room_Number: " + room_number);
        System.out.println("Image: " + image);
    }
}
