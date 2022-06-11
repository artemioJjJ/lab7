/*
package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.collection.CollectionManager;
import ru.itmo.p3131.student18.server.collection.CollectionSaver;

import java.io.Serializable;

public class SaveCommand implements Command, Serializable {
    private CollectionManager manager;

    public SaveCommand(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public int amountOfArgs() {
        return 0;
    }

    @Override
    public void execute(String[] params, String user, HumanBeing human) {
        if (manager.getInputFile() != null) {
            CollectionSaver saver = new CollectionSaver(manager.getInputFile());
            saver.save(manager.getStack());
            String message = "Collection is saved.";
            System.out.println(message);
            Server.printDef(message);
        } else {
            Server.printErr("Collection was not loaded from any file.");
        }
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getCommandInfo() {
        return "saves the collection to the file with starting data.";
    }
}
*/
