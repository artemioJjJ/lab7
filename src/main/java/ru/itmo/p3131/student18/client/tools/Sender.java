package ru.itmo.p3131.student18.client.tools;

import ru.itmo.p3131.student18.interim.messages.ClientMessage;
import ru.itmo.p3131.student18.interim.messages.ServerMessage;
import ru.itmo.p3131.student18.server.exeptions.ConnectionException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Sender {
    private DatagramSocket socket;
    private InetSocketAddress inetSocketAddress;

    private String commandResultMessage = null;
    private String errMessage = null;
    private ServerMessage obj = null;
    public Sender(int port) {
        try {
            socket = new DatagramSocket();
            this.inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
            System.out.println("Client started working!");
        } catch (IllegalArgumentException e) {
            System.out.println("Port parameter is outside the range of valid port values.");
            System.exit(1);
        } catch (SocketException e) {
            System.out.println("Failed to create a new socket.");
        } catch (UnknownHostException e) {
            System.out.println("Address is not valid.");
        }
    }
    public String getCommandResultMessage() {
        String message = commandResultMessage;
        commandResultMessage = null;
        return message;
    }
    public String getErrMessage() {
        String message = errMessage;
        errMessage = null;
        return message;
    }
    public ServerMessage getServerMessage() {
        return obj;
    }
    public void closeSocket() {
        socket.close();
    }

    public void send(ClientMessage message) {
        byte[] bytes = new byte[8192];
        bytes = message.getBytes();
        try {
            DatagramPacket pack = new DatagramPacket(bytes, bytes.length, inetSocketAddress);
            socket.send(pack);
        } catch (IOException e) {
            System.out.println("Failed to send the message.");
        }
    }
    public void receive() throws IOException {
        obj = null;
        byte[] bytes = new byte[8192];
        DatagramPacket pack = new DatagramPacket(bytes, bytes.length);
        socket.receive(pack);
        byte[] byteMessage = pack.getData();
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteMessage));
            obj = (ServerMessage) inputStream.readObject();
            commandResultMessage = obj.getCommandMessage();
            errMessage = obj.getErrorMessage();
            inputStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class exception");
        } catch (NullPointerException e) {
            System.out.println("oh no cringe 2");
        }
    }

}
