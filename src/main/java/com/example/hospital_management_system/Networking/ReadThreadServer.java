package com.example.hospital_management_system.Networking;



import com.example.hospital_management_system.Main;
import com.example.hospital_management_system.patient_page.Patient;

import java.io.IOException;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    public HashMap<String, SocketWrapper> clientMap;

    public ReadThreadServer(HashMap<String, SocketWrapper> map, SocketWrapper socketWrapper) {
        this.clientMap = map;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();

                if(socketWrapper.getName().startsWith("Main")){
                    for(HashMap.Entry<String, SocketWrapper> entry : clientMap.entrySet()){
                        if(!entry.getValue().isClosed() && entry.getValue() != socketWrapper){
                            entry.getValue().write(o);
                            break;
                        }
                    }
                }


                else if (o instanceof Message) {
                    Message obj = (Message) o;
                    String to = obj.getTo();
                    SocketWrapper nu = (SocketWrapper) clientMap.get(to);
                    if (nu != null) {
                        nu.write(obj);
                    }
                }
//                else if(socketWrapper.getType().equals("Resident")){
//                    System.out.println("hi");
//                    String s = (String)o;
//
//                }
                else if (o instanceof String) {
                    String s = (String)o;
                    if(s.startsWith("Res")){
                        String[] spt = s.split("~");
                        System.out.println(spt[1]);
                        for(String st: clientMap.keySet()){
                            if(st.equals(spt[1])){
                                clientMap.get(st).write(spt[2]);
                            }
                        }
//                        String name = Main.patientsMap.getPatient(spt[1]).getName();
//                        clientMap.get(name).write(spt[2]);
                    }
//                    System.out.println("Read message:"+o);
//                    else if(s.equals("#RefreshedSuccessfully")){
//                        socketWrapper.write("#RefreshedSuccessfully");
//                    }
                    else if(clientMap.isEmpty()){
                       // socketWrapper.write("No one available now");
                    }

                    else{
                        for(HashMap.Entry<String, SocketWrapper> entry : clientMap.entrySet()){
                            if(!entry.getValue().isClosed() && entry.getValue() != socketWrapper && entry.getKey().equals("Resident")){
                                entry.getValue().write((String) o + "|"+socketWrapper.getName());
                                break;
                            }
                        }
                    }
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
