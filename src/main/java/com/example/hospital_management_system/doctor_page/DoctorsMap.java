package com.example.hospital_management_system.doctor_page;



import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;

public class DoctorsMap {
    Map<String, Doctor> DoctorMap = new HashMap<>();

    public void addDoctor(Doctor p) {
        DoctorMap.put(p.getId()+"@"+p.getPass(), p);
    }

    public boolean searchDoctor(String userAndPass) {
        return DoctorMap.containsKey(userAndPass);
    }

    public Doctor getDoctor(String userAndPass) {
        return DoctorMap.get(userAndPass);
    }
}
