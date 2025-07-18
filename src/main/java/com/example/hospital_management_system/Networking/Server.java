package com.example.hospital_management_system.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;

    Server() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String clientName = socketWrapper.getName();
        //String clientName = (String) socketWrapper.read();
        clientMap.put(clientName, socketWrapper);
        System.out.println("Connection established with: "+clientName);
        new ReadThreadServer(clientMap, socketWrapper);
    }

    public static void main(String args[]) {

        new Server();
    }
}
