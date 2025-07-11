package com.example.hospital_management_system.patient_page;

import java.util.HashMap;
import java.util.Map;

public class PatientsMap {
     Map<String, Patient> PatientMap = new HashMap<>();

     public void addPatient(Patient p) {
         PatientMap.put(p.getId()+"@"+p.getPass(), p);
     }

     public boolean searchPatient(String userAndPass) {
         return PatientMap.containsKey(userAndPass);
     }

     public Patient getPatient(String userAndPass) {
        return PatientMap.get(userAndPass);
     }


}
