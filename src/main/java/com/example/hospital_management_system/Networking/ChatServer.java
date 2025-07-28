package com.example.hospital_management_system.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {

    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;
    public HashMap<String, SocketWrapper> clientMap2;

    ChatServer() {
        clientMap = new HashMap<>();
        clientMap2 = new HashMap<>();
        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("ChatServer starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
       // String clientName = socketWrapper.getName();
        String clientName = (String) socketWrapper.read();
        if(clientName.startsWith("Main")){
            clientMap2.put(clientName, socketWrapper);
        }
        else clientMap.put(clientName, socketWrapper);
        socketWrapper.setName(clientName);
        System.out.println("Connection established with: "+clientName);
        if(clientName.startsWith("Main")) new ReadThreadServer(clientMap2, socketWrapper);
        else new ReadThreadServer(clientMap, socketWrapper);
    }

    public static void main(String args[]) {

        new ChatServer();
    }
}
