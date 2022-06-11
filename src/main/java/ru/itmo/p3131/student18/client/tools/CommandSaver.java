package ru.itmo.p3131.student18.client.tools;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;

public class CommandSaver {
    private String foundCommand;
    private String[] params;
    private String user;
    private HumanBeing object;

    public String getFoundCommand() {
        return foundCommand;
    }
    public String[] getParams() {
        return params;
    }
    public String getUser() { return user; }
    public HumanBeing getObject() {
        return object;
    }
    public void setFoundCommand(String foundCommand) {
        this.foundCommand = foundCommand;
    }
    public void setParams(String[] params) {
        this.params = params;
    }
    public void setObject(HumanBeing object) {
        this.object = object;
    }
}
