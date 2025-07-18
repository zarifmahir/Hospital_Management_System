package com.example.hospital_management_system.appointment_system;

import java.util.*;

public class AppointmentMap {
    static Map<String, Appointment> AppointmentMap = new HashMap<>();

    public static void addAppointment(Appointment a) {
        AppointmentMap.put(a.getDoctorName() + "@" + a.getDate() + "@" + a.getTime(), a);
    }

    public static boolean searchAppointment(String doctorName, String date, String time) {
        return AppointmentMap.containsKey(doctorName+"@"+date+"@"+time);
    }

    public static Appointment getAppointment(String doctorName, String date, String time) {
        return AppointmentMap.get(doctorName+"@"+date+"@"+time);
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
