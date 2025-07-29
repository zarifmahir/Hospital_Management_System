package com.example.hospital_management_system.appointment_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrescriptionMap {
    Map<String, Prescription> prescriptionMap = new HashMap<>();

    public void addPrescription(Prescription p) {
        prescriptionMap.put(p.getMyId(), p);
        System.out.println("added prescription = " + p.getMyId());
    }

    public int size() {
        return prescriptionMap.size();
    }

    public List<Prescription> getPrescriptionList() {
        return new ArrayList<>(prescriptionMap.values());
    }

    public List<Prescription> getPatientPrescriptionList(String patientId) {
        List<Prescription> res = new ArrayList<>();
        for (Prescription p : prescriptionMap.values()) {
            if (p.getPatientId().equals(patientId)) {
                res.add(p);
            }
        }
        return res;
    }

    public List<Prescription> getDoctorPrescriptionList(String doctorId) {
        List<Prescription> res = new ArrayList<>();
        for (Prescription p : prescriptionMap.values()) {
            if (p.getDoctorId().equals(doctorId)) {
                res.add(p);
            }
        }
        return res;
    }

    public Prescription getPrescription(String myId) {
        return prescriptionMap.get(myId);
    }
//
//    public boolean searchAppointment(String doctorName, String date, String time) {
//        return appointmentMap.containsKey(doctorName+"@"+date+"@"+time);
//    }
}
