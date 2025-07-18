package com.example.hospital_management_system.Networking;



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
                if (o instanceof Message) {
                    Message obj = (Message) o;
                    String to = obj.getTo();
                    SocketWrapper nu = (SocketWrapper) clientMap.get(to);
                    if (nu != null) {
                        nu.write(obj);
                    }
                }
                else if (o instanceof String) {
                    System.out.println("Read message:"+o);
                    if(clientMap.isEmpty()){
                        socketWrapper.write("No one available now");
                    }
                    else{
                        for(HashMap.Entry<String, SocketWrapper> entry : clientMap.entrySet()){
                            if(!entry.getValue().isClosed() && entry.getValue() != socketWrapper){
                                entry.getValue().write((String) o);
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
