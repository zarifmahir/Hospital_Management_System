package com.example.hospital_management_system.Networking;

import com.example.hospital_management_system.patient_page.Patient;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    ReadThreadClient RTC;
   public WriteThreadClient WTC;

    SocketWrapper socketWrapper;
    public Client(String serverAddress, int serverPort, String clientName) {
       // Scanner scanner = null;
        try {
//            System.out.print("Enter name of the client: ");
//            scanner = new Scanner(System.in);
            //String clientName = scanner.nextLine();
           // serverAddress = "192.168.0.117";
            socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write(clientName);
            socketWrapper.setName(clientName);

           RTC = new ReadThreadClient(socketWrapper);
           WTC = new WriteThreadClient(socketWrapper, clientName);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
//            scanner.close();
        }
    }

    public void sendMessage(String message){
        WTC.setMessage(message);
    }

    public void setType(String type){
       socketWrapper.setType(type);
    }

    public void setVboxOfMessages(VBox vboxOfMessages){
        socketWrapper.setvBoxOfMessages(vboxOfMessages);
    }

    public void setObType(Object obType){
        socketWrapper.setO(obType);
    }

//    public Object getMessage() throws IOException, ClassNotFoundException {
//        return  socketWrapper.read();
//    }


//    public static void main(String args[]) {
////        String serverAddress = "127.0.0.1";
////        int serverPort = 44444;
////        new Client(serverAddress, serverPort);
//    }
}
