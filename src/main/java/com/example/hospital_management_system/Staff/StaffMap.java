package com.example.hospital_management_system.Staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffMap {
    Map<String, Nurse> nurseMap = new HashMap<>();
    Map<String, Receptionist> receptionistMap = new HashMap<>();
    Map<String, BedBoy> bedBoyMap = new HashMap<>();

//    public void addAppointment(Appointment a) {
//        appointmentMap.put(a.getDoctorName() + "@" + a.getDate() + "@" + a.getTime(), a);
//    }
//
//    public int size() {
//        return appointmentMap.size();
//    }
//
//    public boolean searchAppointment(String doctorName, String date, String time) {
//        return appointmentMap.containsKey(doctorName+"@"+date+"@"+time);
//    }
//
//    public Appointment getAppointment(String doctorName, String date, String time) {
//        return appointmentMap.get(doctorName+"@"+date+"@"+time);
//    }

    public int size() {
        return nurseMap.size() + receptionistMap.size() + bedBoyMap.size();
    }

    public int nurseCount() {
        return nurseMap.size();
    }

    public int receptionistCount() {
        return receptionistMap.size();
    }

    public int bedBoyCount() {
        return bedBoyMap.size();
    }





    public void addNurse(Nurse n) {
        n.setRole("Nurse");
        nurseMap.put(String.valueOf(n.getId()), n);
    }

    public void addReceptionist(Receptionist r) {
        r.setRole("Receptionist");
        receptionistMap.put(String.valueOf(r.getId()), r);
    }

    public void addBedBoy(BedBoy b) {
        b.setRole("BedBoy");
        bedBoyMap.put(String.valueOf(b.getId()), b);
    }




    public List<Nurse> getNurseList() {
        return new ArrayList<>(nurseMap.values());
    }

    public List<Receptionist> getReceptionistList() {
        return new ArrayList<>(receptionistMap.values());
    }

    public List<BedBoy> getBedBoyList() {
        return new ArrayList<>(bedBoyMap.values());
    }




    public Nurse getNurseById(String nurseId) {
        return nurseMap.get(nurseId);
    }

    public Receptionist getReceptionistById(String receptionistId) {
        return receptionistMap.get(receptionistId);
    }

    public BedBoy getBedBoyById(String bedBoyId) {
        return bedBoyMap.get(bedBoyId);
    }


    public Nurse getNurseByName(String nurseName) {
        for(Nurse nurse : nurseMap.values()){
            if(nurse.getName().equals(nurseName)){
                return nurse;
            }
        }
        return null;
    }

    public Receptionist getReceptionistByName(String receptionistName) {
        for(Receptionist receptionist : receptionistMap.values()){
            if(receptionist.getName().equals(receptionistName)){
                return receptionist;
            }
        }
        return null;
    }
    public BedBoy getBedBoyByName(String bedBoyName) {
        for(BedBoy bedBoy : bedBoyMap.values()){
            if(bedBoy.getName().equals(bedBoyName)){
                return bedBoy;
            }
        }
        return null;
    }
}
