package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.interim.commands.tools.parsers.HumanBeingBuilder;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serializable;
import java.sql.SQLException;

public class UpdateCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public UpdateCommand(CollectionManager manager, StatementControl statementControl) {
        this.manager = manager;
        this.statementControl = statementControl;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        try {
            int updatingObjectId = Integer.parseInt(params[0]);
            if (manager.isIdValid(updatingObjectId)) {
                HumanBeingBuilder parser = new HumanBeingBuilder();
                HumanBeing newHB = parser.update(updatingObjectId, human);
                statementControl.updateObjectById(newHB.getId(), newHB.getName(), newHB.getCoordinates().getX(), newHB.getCoordinates().getY(), newHB.isRealHero(), newHB.isHasToothpick(), newHB.getImpactSpeed(), newHB.getWeaponType(), newHB.getMood(), newHB.getCar().isCool(), newHB.getUser()); ;
                manager.update(newHB);
            } else {
                Server.printErr("There is no element with that id.");
            }
        } catch (NumberFormatException e) {
            Server.printErr("Incorrect value. Parameter value has to be int.");
        } catch (SQLException e) {
            Server.printErr("Failed to add data to database, collection haven't changed either.");
        }
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getCommandInfo() {
        return "updates the element with the given id.";
    }
}
