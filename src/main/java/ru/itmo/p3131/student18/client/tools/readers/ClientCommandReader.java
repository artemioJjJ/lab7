package ru.itmo.p3131.student18.client.tools.readers;

import ru.itmo.p3131.student18.interim.commands.tools.Invoker;
import ru.itmo.p3131.student18.server.exeptions.CommandScannerException;
import ru.itmo.p3131.student18.server.exeptions.ExcessiveArgsException;
import ru.itmo.p3131.student18.server.exeptions.NoArgsException;
import ru.itmo.p3131.student18.interim.commands.tools.CommandManager;

import javax.naming.InvalidNameException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientCommandReader {
    private String foundCommand = null;
    private String[] args = {""};
    private final Invoker invoker = new Invoker(new CommandManager());
    private Scanner scriptDataScanner;

    /**
     * This constructor is used for scanning commands from console on Client side.
     */
    public ClientCommandReader() {}

    /**
     * This constructor is used for reading scanned commands from script file.
     * @param data
     */
    public ClientCommandReader(String data) {
        scriptDataScanner = new Scanner(data);
    }

    public String getFoundCommand() {
        String command = foundCommand;
        foundCommand = null;
        return command;
    }

    public String[] getArgs() {
        String[] arguments = args;
        args = null;
        return arguments;
    }
    /**
     * Method reads new command written in console and checks if command is correct with
     * method commandAndParamsHandling.
     * @throws CommandScannerException if command or parameters are not correct and can not be executed.
     */
    public void startScanning() throws CommandScannerException {
        while (true) {
            System.out.println("Insert a command name or \"help\" to get list of commands:");
            String[] arraysOfParams = {};
            try {
                arraysOfParams = InputStream.nextLine().trim().split(" +");
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            if ("".equals(arraysOfParams[0])) {
                System.out.println("This field can not be empty.");
            } else {
                commandAndParamsHandling(arraysOfParams);
                break;
            }
        }
    }

    /**
     * Method reads next line of data from file containing script commands and checks if command is correct with
     * method commandAndParamsHandling.
     * @return true if there is a next line in scanner with data from script file; false if all commands have been executed.
     * @throws CommandScannerException if command or parameters are not correct and can not be executed.
     */
    public boolean scriptScanning() throws CommandScannerException {
        if (scriptDataScanner.hasNextLine()) {
            String[] arraysOfParams = scriptDataScanner.nextLine().split(" +");
            commandAndParamsHandling(arraysOfParams);

        }
        return scriptDataScanner.hasNextLine();
    }

    /**
     * Private method which gets command name and arguments from method arguments and checks if command is valid. If so,
     * command and params are saved in fields of this ClientCommandReader object for being sent in Client class.
     * @param arraysOfParams String array containing command name at first position and arguments following positions.
     * @throws CommandScannerException if command or parameters are not correct and can not be executed.
     */
    private void commandAndParamsHandling(String[] arraysOfParams) throws CommandScannerException {
        String[] params = {""};
        if (arraysOfParams.length > 1) {
            params = new String[arraysOfParams.length - 1];
            System.arraycopy(arraysOfParams, 1, params, 0, arraysOfParams.length - 1);
        }
        try {
            invoker.commandAndArgsCheck(arraysOfParams[0], params);
            foundCommand = arraysOfParams[0];
            args = params;
            if ("exit".equals(foundCommand)) {
                Thread exitDelay = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                        System.exit(0);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                });
                exitDelay.start();
            }
        } catch (InvalidNameException | NoArgsException | ExcessiveArgsException e) {
            throw new CommandScannerException(e.getMessage());
        }
    }
}
