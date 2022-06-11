package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionLoader;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.exeptions.ObjectFieldsValueException;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.Serializable;
import java.sql.SQLException;

public class ShowCommand implements Command, Serializable {
    private CollectionManager manager;
    private final StatementControl statementControl;

    public ShowCommand(CollectionManager manager, StatementControl statementControl) {
        this.statementControl = statementControl;
        this.manager = manager;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        try {
            manager.init(new CollectionLoader(statementControl.collectionInit()).execute());
            manager.show();
        } catch (SQLException e) {
            Server.printErr("FAil.");
        } catch (ObjectFieldsValueException e) {}
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getCommandInfo() {
        return "returns all elements string representation.";
    }
}
