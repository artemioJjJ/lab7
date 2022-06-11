package ru.itmo.p3131.student18.interim.messages;

import ru.itmo.p3131.student18.interim.commands.Command;
import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class ClientMessage implements Serializable {
    private String command;
    private String[] args;
    private String user;
    private HumanBeing object;

    public ClientMessage(String command, String[] args, String user, HumanBeing object) {
        this.command = command;
        this.args = args;
        this.user = user;
        this.object = object;
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
            System.out.println("Failed to convert client message into bytes.");
        }
        return serializedObj;
    }

    public String getCommandName() {
        return command;
    }
    public String[] getCommandArgs() {
        return args;
    }
    public String getUser() {
        return user;
    }
    public HumanBeing getObject() {
        return object;
    }

    public String toString() {
        return "Received request from " + user + " :\n\t" + "Command: " + command + " " + Arrays.toString(args) + "\n\tObject: " + object;
    }
}
