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

    public List<Appointment> getPatientAppointments(String patientName) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment a : appointmentMap.values()) {
            if (a.getPatientName().equals(patientName)) {
                patientAppointments.add(a);
            }
        }
        return patientAppointments;
    }

    public List<Appointment> getDoctorAppointments(String doctorName) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment a : appointmentMap.values()) {
            if (a.getDoctorName().equals(doctorName)) {
                doctorAppointments.add(a);
            }
        }
        return doctorAppointments;
    }

    public List<Appointment> getDepartmentAppointments(String departmentName) {
        List<Appointment> departmentAppointments = new ArrayList<>();
        for (Appointment a : appointmentMap.values()) {
            if (a.getDepartment().equals(departmentName)) {
                departmentAppointments.add(a);
            }
        }
        return departmentAppointments;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointmentMap.values());
    }
}
