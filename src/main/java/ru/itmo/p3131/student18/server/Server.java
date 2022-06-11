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
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    static String errMessage = "";
    static String defMessage = "";

    public static void printErr(String message) {
        errMessage = errMessage + "\n" + message;
    }

    public static void printDef(String message) {
        defMessage = defMessage + "\n" + message;
    }

    String writeErr() {
        String mes = errMessage;
        errMessage = "";
        return mes;
    }

    String writeDef() {
        String mes = defMessage;
        defMessage = "";
        return mes;
    }

    private Server(int port) {
        this.receiver = new Receiver(port);
        try {
            statementController.startConnection(properties.getUrl(), properties.getAdmin(), properties.getPassword());
            CollectionLoader collectionLoader = new CollectionLoader(statementController.collectionInit());
            collectionManager = new CollectionManager(collectionLoader);
            System.out.println("Collection successfully initialized");
            commandManager = new CommandManager(collectionManager, statementController);
            commandReader = new ServerCommandReader(commandManager);
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
                synchronized (server.receiver) {
                    server.receiver.receive();
                }
            Future<ClientMessage> clientMessageFuture = server.cachedThreadPool.submit(() -> server.receiver.receive());
            ClientMessage message = clientMessageFuture.get();
            server.forkJoinPool.execute(() -> {
                try {
                    server.commandReader.startScanning(message.getCommandName(), message.getCommandArgs(), message.getUser(), message.getObject());
                } catch (CommandScannerException e) {
                    e.printStackTrace();
                }
                new Thread(() -> server.receiver.send(new ServerMessage(server.writeDef(), server.writeErr()))).start();
            });
            } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

