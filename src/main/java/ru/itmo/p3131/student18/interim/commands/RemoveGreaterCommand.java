package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serializable;
import java.sql.SQLException;

public class RemoveGreaterCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public RemoveGreaterCommand(CollectionManager manager, StatementControl statementControl) {
        this.manager = manager;
        this.statementControl = statementControl;
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        int id = Integer.parseInt(params[0]);
        try {
            statementControl.removeGreaterThan(id, user);
            manager.remove_greater(id);
        } catch (SQLException e) {
            Server.printErr("Failed to remove elements greater than " + id);
        }
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getCommandInfo() {
        return "removes all elements, which places further than the element with the given id, from the collection.";
    }
}
