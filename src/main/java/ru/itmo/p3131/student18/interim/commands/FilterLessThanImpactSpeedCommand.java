package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;

import java.io.Serializable;

public class FilterLessThanImpactSpeedCommand implements Command, Serializable {
    private CollectionManager manager;

    public FilterLessThanImpactSpeedCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        try {
            manager.filter_less_than_impact_speed(Float.parseFloat(params[0]));
        } catch (NumberFormatException e) {
            Server.printErr("Incorrect value. Input value has to be float");
        }
    }

    @Override
    public String getName() {
        return "filter_less_than_impact_speed";
    }

    @Override
    public String getCommandInfo() {
        return "returns elements, which \"impactSpeed\" fields's value less than given.";
    }
}
