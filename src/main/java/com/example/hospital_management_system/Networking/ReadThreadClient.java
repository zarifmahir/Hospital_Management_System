package com.example.hospital_management_system.Networking;

import com.example.hospital_management_system.doctor_page.ChatOfDoctorController;
import com.example.hospital_management_system.patient_page.ChatOfPatientController;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;

    public ReadThreadClient(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o instanceof Message) {
                    Message obj = (Message) o;
                    System.out.println(obj.getFrom() + ", " + obj.getTo() + ", " + obj.getText());
                }
                else if (o instanceof String && !((String) o).isEmpty()) {

                    if(socketWrapper.getType().equals("Patient")) ChatOfPatientController.addLabel((String)o, socketWrapper.getvBoxOfMessages());
                    else ChatOfDoctorController.addLabel((String)o, socketWrapper.getvBoxOfMessages());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
