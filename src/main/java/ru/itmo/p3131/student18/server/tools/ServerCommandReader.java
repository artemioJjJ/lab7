package ru.itmo.p3131.student18.server.tools;

import ru.itmo.p3131.student18.interim.commands.tools.Invoker;
import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.exeptions.CommandScannerException;
import ru.itmo.p3131.student18.server.exeptions.ExcessiveArgsException;
import ru.itmo.p3131.student18.server.exeptions.NoArgsException;
import ru.itmo.p3131.student18.interim.commands.tools.CommandManager;

import javax.naming.InvalidNameException;

public class ServerCommandReader {
    private final Invoker invoker;

    public ServerCommandReader(CommandManager commandManager) {
        this.invoker = new Invoker(commandManager);
    }

    public void startScanning(String command, String[] args, String user, HumanBeing human) throws CommandScannerException {
        try {
            switch (command) {
                case "register", "login" -> invoker.userHandling(command, args);
                default -> invoker.findCommand(command, args, user, human);
            }
        } catch (InvalidNameException | NoArgsException | ExcessiveArgsException e) {
            throw new CommandScannerException(e.getMessage());
        }
    }
}
