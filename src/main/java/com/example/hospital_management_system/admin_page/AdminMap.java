package com.example.hospital_management_system.admin_page;

import com.example.hospital_management_system.doctor_page.Doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminMap {
    Map<String, Admin> adminMap = new HashMap<>();

    public void addAdmin(Admin admin) {
        adminMap.put(admin.getUser()+"@"+admin.getPassword(), admin);
    }

    public int size() {
        return adminMap.size();
    }

    public boolean searchAdmin(String userAndPass) {
        return adminMap.containsKey(userAndPass);
    }

    public Admin getAdmin(String userAndPass) {
        return adminMap.get(userAndPass);
    }

}
