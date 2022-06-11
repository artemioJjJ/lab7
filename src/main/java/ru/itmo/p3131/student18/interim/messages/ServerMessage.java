package ru.itmo.p3131.student18.interim.messages;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ServerMessage implements Serializable {
    private final String commandMessage;
    private final String errorMessage;

    public ServerMessage(String commandMessage, String errorMessage) {
        this.commandMessage = commandMessage;
        this.errorMessage = errorMessage;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public byte[] getBytes()  {
        byte[] serializedObj = {};
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            serializedObj = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (NullPointerException e) {
            System.out.println("Epic fail - null.");
        } catch (IOException e) {
            System.out.println("Failed to get bytes from server message.");
        }
        return serializedObj;
    }
}




















