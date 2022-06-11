package ru.itmo.p3131.student18.interim.commands;


import ru.itmo.p3131.student18.interim.commands.tools.CommandManager;
import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;

import java.io.Serializable;
import java.util.function.Consumer;

public class HelpCommand implements Command, Serializable {
    private CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void execute(String[] params, String user, HumanBeing human) {
        Consumer<Command> consumer = command -> Server.printDef("- " + command.getName() + ": "+ command.getCommandInfo());
        commandManager.getCommands().values().forEach(consumer);
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    public String getName() {
        return "help";
    }

    @Override
    public String getCommandInfo() {
        return "returns information about available commands.";
    }
}
