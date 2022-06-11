package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serializable;
import java.sql.SQLException;

public class RemoveCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public RemoveCommand(CollectionManager manager, StatementControl statementControl) {
        this.manager = manager;
        this.statementControl = statementControl;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        try {
            int objId = Integer.parseInt(params[0]);
            if (manager.isIdValid(objId)) {
                statementControl.removeObjectById(objId, user);
                manager.remove_by_id(objId);
            } else {
                Server.printErr("There is no element with that id.");
            }
        } catch (NumberFormatException e) {
            Server.printErr("Incorrect value. Parameter value has to be int.");
        } catch (SQLException e) {
            Server.printErr("Failed to remove element from database.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getCommandInfo() {
        return "removes an element from collection by it's id (if there is an element with the given id).";
    }
}
