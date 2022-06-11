package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.sql.SQLException;

public class RegisterCommand implements Command {
    private final StatementControl statementController;

    public RegisterCommand(StatementControl statementController) {
        this.statementController = statementController;
    }

    @Override
    public void execute(String[] params) {
        try {
            String insertedLogin = params[0];
            String insertedEncodedPassword = params[1];
            statementController.registerUser(insertedLogin, insertedEncodedPassword);
            Server.printDef("User has successfully registered.");
        } catch (SQLException e) {
            Server.printErr("Failed to register a new user.");
        }
    }
    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "register";
    }

    @Override
    public String getCommandInfo() {
        return "creates a new user to get access to Database.";
    }
}
