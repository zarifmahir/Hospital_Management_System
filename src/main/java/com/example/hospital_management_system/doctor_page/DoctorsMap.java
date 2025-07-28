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

    public int size() {
        return DoctorMap.size();
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

    public Doctor getDoctorById(String id) {
        for(Doctor doctor : DoctorMap.values()){
            if(doctor.getId().equals(id)){
                return doctor;
            }
        }
        return null;
    }

    public List<String> getDepartments() {
        List<String> departments = new ArrayList<>();
        departments.add("Medicine");
        departments.add("Surgery");
        departments.add("Pediatrics");
        departments.add("Obstetrics");
        departments.add("Gynecology");
        departments.add("Orthopedics");
        departments.add("Cardiology");
        departments.add("Neurology");
        departments.add("Pathology");
        departments.add("Dermatology");
        departments.add("Psychiatry");

        return departments;
    }

}
