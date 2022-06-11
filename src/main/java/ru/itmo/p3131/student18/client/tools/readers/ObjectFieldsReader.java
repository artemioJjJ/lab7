package ru.itmo.p3131.student18.client.tools.readers;

import ru.itmo.p3131.student18.server.exeptions.NumberValueException;
import ru.itmo.p3131.student18.interim.objectclasses.*;

public class ObjectFieldsReader {

    public String nameScanner() {
        String name = "";
        System.out.println("Insert a name(String):");
        while (true) {
            name = InputStream.nextLine().trim();
            if (name.equals("") || name.split(" +").length > 1) {
                System.out.println("Incorrect name. Name can't be empty or consist of more than one word or be empty.");
            } else {
                break;
            }
        }
        return name;
    }

    public Coordinates coordinatesScanner() {
        boolean confirmationX = false;
        boolean confirmationY = false;
        long x = 0;
        long y = 0;
        System.out.println("Insert coordinates:");
        while (!confirmationX) {
            System.out.print("\tInsert x (Long): ");
            try {
                x = Long.parseLong(InputStream.nextLine().trim());
                confirmationX = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value. Input value has to be Long");
            }
        }
        while (!confirmationY) {
            System.out.print("\tInsert y (Long): ");
            try {
                y = Long.parseLong(InputStream.nextLine().trim());
                confirmationY = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value. Input value has to be Long");
            }
        }
        return new Coordinates(x, y);
    }

    public boolean realHeroScanner() {
        boolean confirmation = false;
        boolean realHero = false;
        System.out.println("Insert realHero value(true/false):");
        while (!confirmation) {
            String value = InputStream.nextLine().trim();
            if ("true".equalsIgnoreCase(value)) {
                realHero = true;
                confirmation = true;
            } else if ("false".equalsIgnoreCase(value)) {
                confirmation = true;
            } else if ("".equals(value)) {
                System.out.println("This field can not be empty.");
            } else {
                System.out.println("Incorrect value. Insert \"true\" or \"false\".");
            }
        }
        return realHero;
    }

    public boolean hasToothPickScanner() {
        boolean confirmation = false;
        boolean hasToothPick = false;
        System.out.println("Insert hasToothPick value(true/false):");
        while (!confirmation) {
            String value = InputStream.nextLine().trim();
            if (value.equalsIgnoreCase("true")) {
                hasToothPick = true;
                confirmation = true;
            } else if (value.equalsIgnoreCase("false")) {
                confirmation = true;
            } else if ("".equals(value)) {
                System.out.println("This field can not be empty.");
            } else {
                System.out.println("Reading error. Insert \"true\" or \"false\".");
            }
        }
        return hasToothPick;
    }

    public float impactSpeedScanner() {
        boolean confirmation = false;
        float impactSpeed = 0.0f;
        System.out.println("Insert impact speed (float): ");
        while (!confirmation) {
            try {
                impactSpeed = Float.parseFloat(InputStream.nextLine().trim());
                if (impactSpeed <= -163) {
                    throw new NumberValueException("Incorrect value. Impact speed value has to be more than -163.0");
                }
                confirmation = true;
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect value. Input value has to be float");
            } catch (NumberValueException e) {
                System.out.println(e.getMessage());
            }
        }
        return impactSpeed;
    }

    public WeaponType weaponScanner() {
        boolean confirmation = false;
        String weaponContainer = null;
        while (!confirmation) {
            System.out.println("Insert weapon type:" +
                    "\n\tHammer\n\tAxe\n\tShotgun\n\tRifle\n\tMachine_gun");
            try {
                weaponContainer = InputStream.nextLine().toUpperCase().trim();
                WeaponType.valueOf(weaponContainer);
                confirmation = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Inserted value doesn't match any of suggested values." +
                        "\nMake sure that you use \"_\" instead of \"space\" bar.");
            }
        }
        return WeaponType.valueOf(weaponContainer);
    }

    public Mood moodScanner() {
        String moodContainer = null;
        while (moodContainer == null) {
            System.out.println("Insert mood:" +
                    "\n\tSadness\n\tLonging\n\tGloom\n\tRage" +
                    "\n This field can be empty, if you press \"Enter\" button.");
            moodContainer = InputStream.nextLine().toUpperCase().trim();
            if (moodContainer.equals("")) {
                System.out.println("This value can not be empty.");
            } else {
                try {
                    Mood.valueOf(moodContainer);
                } catch (IllegalArgumentException e) {
                    System.out.println("Inserted value doesn't match any of suggested values.");
                }
            }
        }
        return Mood.valueOf(moodContainer);
    }

    public Car carScanner() {
        boolean confirmation = false;
        boolean isCarCool = false;
        String value;
        System.out.println("Insert car's value of \"cool\" (true / false):");
        while (!confirmation) {
            value = InputStream.nextLine().trim();
            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")) {
                isCarCool = true;
                confirmation = true;
            } else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("f")) {
                confirmation = true;
            } else {
                System.out.println("Reading error. Insert \"true\" or \"false\".");
            }
        }
        return new Car(isCarCool);
    }
}
