package ru.itmo.p3131.student18.server.tools;

import ru.itmo.p3131.student18.interim.messages.ClientMessage;
import ru.itmo.p3131.student18.interim.messages.ServerMessage;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.exeptions.CommandScannerException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

public class Receiver  implements Runnable {
    private DatagramSocket serverSocket;
    private InetSocketAddress recentAddress;
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private ServerCommandReader serverCommandReader;
    private int oldPort = 0;

    public int getOldPort() {
        return oldPort;
    }

    public void closeSocket() {
        serverSocket.close();
    }

    private void isMessageFromNewClient(int newPort) {
        if (newPort != getOldPort()) {
            System.out.println("New client. Port: " + newPort);
        }
    }

    /**Constructs a receiver object with own Datagram Socket, connected to random accessed local port.
     *
     * @param port - a port on a local host machine to be bound by socket.
     * @throws SocketException - if socket could not be opened, or the socket could not bind to the specified local port.
     */
    public Receiver(int port, ServerCommandReader serverCommandReader) {
        this.serverCommandReader = serverCommandReader;
        try {
            this.serverSocket = new DatagramSocket(port);
            System.out.println("Server started. Port: " + port);
        } catch (SocketException e) {
            System.out.println("Socket could not be opened, or the socket could not bind to the specified local port.");
            System.exit(1);
        }
    }

    /**Method is waiting until it receives a DatagramPacket from Client. When a datagram is received it deserializes to ClientMessage object.
     *
     * @throws IOException if something is wrong with receiving the data from socket.
     */
    public void receive() throws IOException {
        byte[] message = new byte[8192];
        DatagramPacket pack = new DatagramPacket(message, message.length);
        serverSocket.receive(pack);
        isMessageFromNewClient(pack.getPort());
        recentAddress = new InetSocketAddress(InetAddress.getLocalHost(), pack.getPort());
        oldPort = pack.getPort();
        byte[] data = pack.getData();
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            ClientMessage clientMessage = (ClientMessage) inputStream.readObject();
            System.out.println("Received: " + clientMessage.getCommandName() + " " + (Objects.equals(clientMessage.getCommandArgs(),
                    null) ? "" : " " + clientMessage.getCommandArgs()[0]));
            inputStream.close();
            forkJoinPool.execute(() -> {
                try {
                    serverCommandReader.startScanning(clientMessage.getCommandName(), clientMessage.getCommandArgs(), clientMessage.getUser(), clientMessage.getObject());
                    new Thread(() -> {
                        send(new ServerMessage(Server.writeDef(), Server.writeErr()));
                    }).start();
                } catch (CommandScannerException e) {
                    e.printStackTrace();
                }

            });

        } catch (ClassNotFoundException e) {
            System.out.println("Class exception");
        }
    }

    public void send(ServerMessage message) {
        byte[] bytes;
        bytes = message.getBytes();
        try {
            DatagramPacket notification = new DatagramPacket(bytes, bytes.length, recentAddress);
            serverSocket.send(notification);
        } catch (IOException e) {
            System.out.println("Failed to send server message.");
        }
    }

    public void run() {
        try {
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
