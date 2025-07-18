package com.example.hospital_management_system.Networking;


import java.io.IOException;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {

    private Thread thr;
    private SocketWrapper socketWrapper;
    String name;
    private String msg;
    private boolean status;

    public WriteThreadClient(SocketWrapper socketWrapper, String name) {
        this.socketWrapper = socketWrapper;
        status = false;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void setMessage(String msg) {
        System.out.println("Message set");
        this.msg = msg;
        status = true;
    }

    public void run() {
        Scanner input = null;
        try {
            input = new Scanner(System.in);
            while (true) {
                //          String from = name;
//                System.out.print("Enter name of the client to send: ");
//                String to = input.nextLine();
//                System.out.print("Enter the message: ");
//                String text = input.nextLine();
//                Message message = new Message();
//                message.setFrom(from);
//                message.setTo(to);
//                message.setText(text);
//                socketWrapper.write(message);

                if(status){
                    System.out.println("written message");
                    socketWrapper.write(msg);
                    status = false;
                }
                try {
                    Thread.sleep(50); // avoid busy-loop
                } catch (InterruptedException e) {
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
