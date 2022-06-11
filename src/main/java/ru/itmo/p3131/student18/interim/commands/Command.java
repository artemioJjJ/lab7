package ru.itmo.p3131.student18.interim.commands;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;

public interface Command {
    int amountOfArgs();
    default void execute(String[] params) {}
    default void execute(String[] params, String user, HumanBeing human) {}
    String getName();
    String getCommandInfo();
}
