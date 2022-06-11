package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.utils.ResultSetReader;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class LoginCommand implements Command {
    private final StatementControl statementController;
    private final ResultSetReader resultSetReader = new ResultSetReader();

    public LoginCommand(StatementControl statementController) {
        this.statementController = statementController;
    }

    @Override
    public void execute(String[] params) {
        try {
            String insertedLogin = params[0];
            String insertedEncryptedPassword = params[1];
            String dbPassword = resultSetReader.getPasswordFromLoginUser(statementController.loginUser(insertedLogin));
            if (insertedEncryptedPassword.equals(dbPassword)) {
                Server.printDef("User has successfully logged in.");
            }
        } catch (SQLException e) {
            Server.printErr("Failed to login user.");
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            Server.printErr(e.getMessage());
            System.out.println("oh no cringe3");
        }
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getCommandInfo() {
        return "makes user authorization for getting access to other commands.";
    }
}
