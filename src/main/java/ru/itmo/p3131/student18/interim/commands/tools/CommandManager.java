package ru.itmo.p3131.student18.interim.commands.tools;

import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.interim.commands.*;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandManager() {
        registerCommand(new HelpCommand(this));
        registerCommand(new AddCommand(null, null));
        registerCommand(new InfoCommand(null));
        registerCommand(new ShowCommand(null, null));
        registerCommand(new UpdateCommand(null, null));
        registerCommand(new RemoveCommand(null, null));
        registerCommand(new ClearCommand(null, null));
        registerCommand(new ExitCommand(null));
        registerCommand(new RemoveFirstCommand(null, null));
        registerCommand(new RemoveLastCommand(null, null));
        registerCommand(new RemoveGreaterCommand(null, null));
        registerCommand(new CountByImpactSpeedCommand(null));
        registerCommand(new FilterStartsWithNameCommand(null));
        registerCommand(new FilterLessThanImpactSpeedCommand(null));
        registerCommand(new ExecuteScriptCommand(null));
        registerCommand(new LoginCommand(null));
        registerCommand(new RegisterCommand(null));
    }
    public CommandManager(CollectionManager manager, StatementControl statementControl) {
        registerCommand(new HelpCommand(this));
        registerCommand(new AddCommand(manager, statementControl));
        registerCommand(new InfoCommand(manager));
        registerCommand(new ShowCommand(manager, statementControl));
        registerCommand(new UpdateCommand(manager, statementControl));
        registerCommand(new RemoveCommand(manager, statementControl));
        registerCommand(new ClearCommand(manager, statementControl));
        registerCommand(new ExitCommand(manager));
        registerCommand(new RemoveFirstCommand(manager, statementControl));
        registerCommand(new RemoveLastCommand(manager, statementControl));
        registerCommand(new RemoveGreaterCommand(manager, statementControl));
        registerCommand(new CountByImpactSpeedCommand(manager));
        registerCommand(new FilterStartsWithNameCommand(manager));
        registerCommand(new FilterLessThanImpactSpeedCommand(manager));
        registerCommand(new ExecuteScriptCommand(manager));
        registerCommand(new LoginCommand(statementControl));
        registerCommand(new RegisterCommand(statementControl));
    }

    public Map getCommands() {
        return commands;
    }

    /**
     * Method of a class CommandManager which returns a Command by its name, if this command is registered in
     * HashMap commands of class CommandManager.
     *
     * @param name name of command according to Command's getName method.
     * @return Command with the given name.
     */
    public Command getSpecificCommand(String name) {
        return commands.get(name.toLowerCase());
    }

    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

}
