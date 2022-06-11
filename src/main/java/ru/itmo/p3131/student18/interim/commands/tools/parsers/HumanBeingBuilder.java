package ru.itmo.p3131.student18.interim.commands.tools.parsers;

import ru.itmo.p3131.student18.client.tools.readers.ObjectFieldsReader;
import ru.itmo.p3131.student18.interim.commands.tools.objectcheck.HumanBeingChecker;
import ru.itmo.p3131.student18.interim.objectclasses.*;

import java.time.LocalDate;

public class HumanBeingBuilder {

    private String name;
    private Coordinates coordinates;
    private boolean realHero;
    private boolean hasToothpick;
    private float impactSpeed; //Значение поля должно быть больше -163
    private WeaponType weaponType;
    private Mood mood;
    private Car car;
    private String user;

    private final HumanBeingChecker checker = new HumanBeingChecker();
    private ObjectFieldsReader reader;

    public HumanBeingBuilder() {
        reader = new ObjectFieldsReader();
    }

    public HumanBeing create(String user) {
        check();
        return new HumanBeing(name, coordinates, realHero, hasToothpick, impactSpeed, weaponType, mood, car, user);
    }

    public HumanBeing create(int id, String name, Coordinates coordinates, LocalDate creationDate, boolean realHero, boolean hasToothPick, float impactSpeed, WeaponType weaponType, Mood mood, Car car, String user) {
        return new HumanBeing(id, name, coordinates, creationDate, realHero, hasToothPick, impactSpeed, weaponType, mood, car, user);
    }
    /**
     * Server side method
     * @param newId
     * @param human
     * @return
     */
    public HumanBeing update(int newId, HumanBeing human) {
        check(human.getName(), human.getCoordinates(), human.isRealHero(), human.isHasToothpick(), human.getImpactSpeed(), human.getWeaponType(), human.getMood(), human.getCar(), human.getUser());
        HumanBeing updatedHumanBeing = new HumanBeing(name, coordinates, realHero, hasToothpick, impactSpeed, weaponType, mood, car, user);
        updatedHumanBeing.setId(newId);
        return updatedHumanBeing;
    }

    private void check() {
        while (!checker.isNameCorrect()) {
            name = checker.nameCheck(reader.nameScanner());
        }
        while (!checker.isCoordinatesCorrect()) {
            coordinates = checker.coordinatesCheck(reader.coordinatesScanner());
        }
        // Boolean values can not be null so they are validated in ObjectFieldsReader.
        realHero = reader.realHeroScanner();
        hasToothpick = reader.hasToothPickScanner();

        while (!checker.isImpactSpeedCorrect()) {
            impactSpeed = checker.impactSpeedCheck(reader.impactSpeedScanner());
        }
        // Can't go wrong with Enums as well. Exceptions will be caught at gson parsing or at reading validation.
        weaponType = reader.weaponScanner();
        mood = reader.moodScanner();
        car = reader.carScanner();
    }

    private void check(String name, Coordinates coordinates, boolean realHero, boolean hasToothPick, float impactSpeed, WeaponType weaponType, Mood mood, Car car, String user) {
        while (!checker.isNameCorrect()) {
            this.name = checker.nameCheck(name);
        }
        while (!checker.isCoordinatesCorrect()) {
            this.coordinates = checker.coordinatesCheck(coordinates);
        }
        while (!checker.isImpactSpeedCorrect()) {
            this.impactSpeed = checker.impactSpeedCheck(impactSpeed);
        }
        this.realHero = realHero;
        this.hasToothpick = hasToothPick;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        this.user = user;
    }
}
