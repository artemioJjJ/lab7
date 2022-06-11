package ru.itmo.p3131.student18.interim.commands.tools.objectcheck;

import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.exeptions.NumberValueException;
import ru.itmo.p3131.student18.interim.objectclasses.Coordinates;

public class HumanBeingChecker {

    private boolean nameCorrect = false;
    private boolean coordinatesCorrect = false;
    private boolean impactSpeedCorrect = false;

    public boolean isNameCorrect() {
        return nameCorrect;
    }
    public boolean isCoordinatesCorrect() {
        return coordinatesCorrect;
    }
    public boolean isImpactSpeedCorrect() {
        return impactSpeedCorrect;
    }

    public String nameCheck(String name) {
        if (name.equals("") || name.split(" +").length > 1) {
            String mes = "Incorrect name. Name can't be empty or consist of more than one word.";
            System.out.println(mes);
        } else {
            nameCorrect = true;
        }
        return name;
    }

    public Coordinates coordinatesCheck(Coordinates coordinates) {
        if (coordinates == null) {
            System.out.println("Coordinates value can not be \"null\".");
        } else {
            coordinatesCorrect = true;
        }
        return coordinates;
    }

    public float impactSpeedCheck(float impactSpeed) {
        try {
            if (impactSpeed <= -163) {
                throw new NumberValueException("\n Incorrect value. Impact speed value has to be more than -163.0");
            } else {
                impactSpeedCorrect = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nIncorrect value. Input value has to be float");
        } catch (NumberValueException e) {
            System.out.println(e.getMessage());
        }
        return impactSpeed;
    }


}
