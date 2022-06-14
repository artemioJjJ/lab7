package ru.itmo.p3131.student18.server;

import ru.itmo.p3131.student18.interim.commands.tools.CommandManager;
import ru.itmo.p3131.student18.interim.messages.ClientMessage;
import ru.itmo.p3131.student18.interim.messages.ServerMessage;
import ru.itmo.p3131.student18.server.collection.CollectionLoader;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.exeptions.CommandScannerException;
import ru.itmo.p3131.student18.server.exeptions.ObjectFieldsValueException;
import ru.itmo.p3131.student18.server.tools.Receiver;
import ru.itmo.p3131.student18.server.tools.ServerCommandReader;
import ru.itmo.p3131.student18.server.utils.PropertyReader;
import ru.itmo.p3131.student18.server.utils.StatementControl;

import java.io.*;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.concurrent.*;

public class Server {
    private final Receiver receiver;
    private final PropertyReader properties = new PropertyReader();
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private ServerCommandReader commandReader;
    private final StatementControl statementController = new StatementControl();
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    static String errMessage = "";
    static String defMessage = "";

    public static void printErr(String message) {
        errMessage = errMessage + "\n" + message;
    }

    public static void printDef(String message) {
        defMessage = defMessage + "\n" + message;
    }

    public static String writeErr() {
        String mes = errMessage;
        errMessage = "";
        return mes;
    }

    public static String writeDef() {
        String mes = defMessage;
        defMessage = "";
        return mes;
    }

    private Server(int port) {
        collectionManager = new CollectionManager();
        commandManager = new CommandManager(collectionManager, statementController);
        commandReader = new ServerCommandReader(commandManager);
        receiver = new Receiver(port, commandReader);
        try {
            statementController.startConnection(properties.getUrl(), properties.getAdmin(), properties.getPassword());
            collectionManager.init(new CollectionLoader(statementController.collectionInit()));
            System.out.println("Collection successfully initialized");
        } catch (ObjectFieldsValueException | SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server stops working. This is the end.");

        }));
        Server server = new Server(9000);
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
                server.cachedThreadPool.execute(server.receiver);
            }
    }
}

