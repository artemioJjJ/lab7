package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;

import java.io.Serializable;

public class CountByImpactSpeedCommand implements Command, Serializable {
    private CollectionManager manager;

    public CountByImpactSpeedCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        try {
            manager.count_by_impact_speed(Float.parseFloat(params[0]));
        } catch (NumberFormatException e) {
            Server.printErr("Incorrect value. Input value has to be float");
        }
    }

    @Override
    public String getName() {
        return "count_by_impact_speed";
    }

    @Override
    public String getCommandInfo() {
        return "return a number of elements, which field \"impactSpeed\" is equal to the given value.";
    }
}
