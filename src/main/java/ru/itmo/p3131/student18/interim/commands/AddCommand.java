package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serializable;
import java.sql.SQLException;

public class AddCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public AddCommand(CollectionManager manager, StatementControl statementControl) {
        this.manager = manager;
        this.statementControl = statementControl;
    }

    /**
     * Server side method for adding a new object to the collection.
     * @param args
     * @param human
     */
    @Override
    public void execute(String[] args, String user, HumanBeing human) {
        try {
            statementControl.addNewObject(human.getName(), human.getCoordinates().getX(), human.getCoordinates().getY(), human.isRealHero(), human.isHasToothpick(), human.getImpactSpeed(), human.getWeaponType(), human.getMood(), human.getCar().isCool(), human.getUser());
            manager.add(human);
            Server.printDef("Element has successfully added.");
        } catch (SQLException e) {
            Server.printErr("Failed to add element to the database.");
        }

    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getCommandInfo() {
        return "add a new element to the collection.";
    }
}

