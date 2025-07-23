package com.example.hospital_management_system.patient_page;

import java.util.HashMap;
import java.util.Map;

public class PatientChatMap {
    public Map<Patient, String> chatMap = new HashMap<>();

    public void addChat(Patient patient, String message) {
        chatMap.put(patient, message);
    }

    public boolean containsChat(Patient patient) {
      // System.out.println(patient.getName());
        return chatMap.containsKey(patient);
    }

    public String getChat(Patient patient){
        return chatMap.get(patient);
    }

}
