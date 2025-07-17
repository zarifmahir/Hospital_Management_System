package com.example.hospital_management_system.doctor_page;



import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsMap {
    Map<String, Doctor> DoctorMap = new HashMap<>();

    public void addDoctor(Doctor p) {
        DoctorMap.put(p.getUserName()+"@"+p.getPass(), p);
    }

    public boolean searchDoctor(String userAndPass) {
        return DoctorMap.containsKey(userAndPass);
    }

    public Doctor getDoctor(String userAndPass) {
        return DoctorMap.get(userAndPass);
    }

    public List<Doctor> getDoctorList() {
        List<Doctor> doctorList = new ArrayList<>();
        for (Map.Entry<String, Doctor> entry : DoctorMap.entrySet()) {
            doctorList.add(entry.getValue());
        }
        return doctorList;
    }

    public List<Doctor> getDepartmentWiseDoctors(String department) {
        List<Doctor> doctorList = new ArrayList<>();
        for (Map.Entry<String, Doctor> entry : DoctorMap.entrySet()) {
            if (entry.getValue().getDepartment().equals(department)) {
                doctorList.add(entry.getValue());
            }
        }
        return doctorList;
    }

}
