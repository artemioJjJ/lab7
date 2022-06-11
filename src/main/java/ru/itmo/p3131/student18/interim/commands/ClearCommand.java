package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serializable;
import java.sql.SQLException;

public class ClearCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public ClearCommand(CollectionManager manager, StatementControl statementControl) {
        this.manager = manager;
        this.statementControl = statementControl;
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        try {
            System.out.println(user);
            statementControl.clearByUserName(user);
            manager.clear();
        } catch (SQLException e) {
            Server.printErr("Failed to delete element, related to the user.");
        }
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getCommandInfo() {
        return "remove all elements from the collection.";
    }
}

