package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.collection.CollectionManager;

import java.io.Serializable;

public class InfoCommand implements Command, Serializable {
    private CollectionManager manager;

    public InfoCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        manager.info();
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getCommandInfo() {
        return "return information about the collection.";
    }

}
