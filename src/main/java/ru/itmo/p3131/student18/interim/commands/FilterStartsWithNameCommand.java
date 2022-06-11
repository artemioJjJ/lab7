package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.collection.CollectionManager;

import java.io.Serializable;

public class FilterStartsWithNameCommand implements Command, Serializable {
    private CollectionManager manager;

    public FilterStartsWithNameCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        manager.filter_starts_with_name(params[0]);
    }

    @Override
    public String getName() {
        return "filter_starts_with_name";
    }

    @Override
    public String getCommandInfo() {
        return "return elements, which \"name\" value starts with the given substring.";
    }
}
