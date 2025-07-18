package com.example.hospital_management_system.Networking;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketWrapper {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean sendStatus;
    private String type;
    private VBox vBoxOfMessages;

    private String name;

    public SocketWrapper(String s, int port) throws IOException { // used by the client
        this.socket = new Socket(s, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        sendStatus = false;
    }

    public SocketWrapper(Socket s) throws IOException { // used by the server
        this.socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        sendStatus = true;
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readObject();
    }

    public void write(Object o) throws IOException {
        oos.writeObject(o);

    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }

    public boolean getSendStatus(){
        return sendStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VBox getvBoxOfMessages() {
        return vBoxOfMessages;
    }

    public void setvBoxOfMessages(VBox vBoxOfMessages) {
        this.vBoxOfMessages = vBoxOfMessages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
