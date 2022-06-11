package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

public class RemoveLastCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public RemoveLastCommand(CollectionManager manager, StatementControl statementControl) {
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
            statementControl.removeLast(user);
            manager.remove_last();
        } catch (SQLException e) {
            Server.printErr("Failed to remove the last element.");
        }
    }

    @Override
    public String getName() {
        return "remove_last";
    }

    @Override
    public String getCommandInfo() {
        return "removes the last element from the collection.";
    }
}
