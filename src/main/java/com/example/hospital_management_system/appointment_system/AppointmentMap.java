package com.example.hospital_management_system.appointment_system;

import java.util.*;

public class AppointmentMap {
    Map<String, Appointment> appointmentMap = new HashMap<>();

    public void addAppointment(Appointment a) {
        appointmentMap.put(a.getDoctorName() + "@" + a.getDate() + "@" + a.getTime(), a);
    }

    public int size() {
        return appointmentMap.size();
    }

    public boolean searchAppointment(String doctorName, String date, String time) {
        return appointmentMap.containsKey(doctorName+"@"+date+"@"+time);
    }

    public Appointment getAppointment(String doctorName, String date, String time) {
        return appointmentMap.get(doctorName+"@"+date+"@"+time);
    }

//    public List<Doctor> getDoctorList() {
//        List<Doctor> doctorList = new ArrayList<>();
//        for (Map.Entry<String, Doctor> entry : DoctorMap.entrySet()) {
//            doctorList.add(entry.getValue());
//        }
//        return doctorList;
//    }
//
//    public List<Doctor> getDepartmentWiseDoctors(String department) {
//        List<Doctor> doctorList = new ArrayList<>();
//        for (Map.Entry<String, Doctor> entry : DoctorMap.entrySet()) {
//            if (entry.getValue().getDepartment().equals(department)) {
//                doctorList.add(entry.getValue());
//            }
//        }
//        return doctorList;
//    }
}
