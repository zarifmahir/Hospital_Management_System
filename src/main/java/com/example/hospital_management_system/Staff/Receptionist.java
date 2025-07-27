package com.example.hospital_management_system.Staff;

public class Receptionist extends Staff {
    public static int id = 1;
    public int myId;

    public Receptionist(String name, String department, String yearsOfExperience, String phoneNumber, String email, String address) {
        super(name, department, yearsOfExperience, phoneNumber, email, address);
        this.myId = id++;
    }

    public void setId(int myId) {
        this.myId = myId;
    }

    public int getId() {
        return myId;
    }
}
