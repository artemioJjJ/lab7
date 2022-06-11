package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;

import java.io.Serializable;


public class ExecuteScriptCommand implements Command, Serializable {

    private CollectionManager manager;

    public ExecuteScriptCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public int amountOfArgs() {
        return 1;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        String message = "Script execution started";
        System.out.println(message);
        Server.printDef(message);
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getCommandInfo() {
        return "read a script from the file with given filename. Commands in the file \n\t" +
                "should be similar to what user would write in console.";
    }
}
