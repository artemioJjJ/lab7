package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.collection.CollectionSaver;

import java.io.Serializable;

public class ExitCommand implements Command, Serializable {
    private CollectionManager manager;

    public ExitCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        System.out.println("Client has ended a session.");
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getCommandInfo() {
        return "stops user interaction with the collection and saves it.";
    }
}
