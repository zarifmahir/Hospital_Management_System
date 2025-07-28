package com.example.hospital_management_system.patient_page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientsMap {
    public Map<String, Patient> PatientMap = new HashMap<>();

     public void addPatient(Patient p) {
         PatientMap.put(p.getUsername()+"@"+p.getPass(), p);
     }

     public boolean searchPatient(String userAndPass) {
         return PatientMap.containsKey(userAndPass);
     }

     public Patient getPatient(String userAndPass) {
        return PatientMap.get(userAndPass);
     }

     public int size() {
         return PatientMap.size();
     }

     public List<Patient> getPatientList() {
         List<Patient> patientList = new ArrayList<>();
         for(Map.Entry<String, Patient> entry : PatientMap.entrySet()){
             patientList.add(entry.getValue());
         }
         return patientList;
     }

    public Patient getPatientById(String id) {
         for (Patient p : PatientMap.values()) {
             if (p.getId().equals(id)) {
                 return p;
             }
         }
         return null;
    }
}
