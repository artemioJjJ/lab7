package ru.itmo.p3131.student18.server.collection;

import ru.itmo.p3131.student18.interim.commands.tools.parsers.HumanBeingBuilder;
import ru.itmo.p3131.student18.interim.objectclasses.*;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.exeptions.ObjectFieldsValueException;
import ru.itmo.p3131.student18.server.utils.ResultSetReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CollectionLoader {
    private final ResultSet resultSet;
    private final ResultSetReader resultSetReader = new ResultSetReader();

    public CollectionLoader(ResultSet set)  {
        this.resultSet = set;
    }

    public Stack<HumanBeing> execute() throws ObjectFieldsValueException, SQLException {
        Stack<HumanBeing> tmpStack = new Stack<>();
        while (true) {
            try {
                ArrayList al = resultSetReader.getHumanBeingTableFromCollectionInit(resultSet);
                tmpStack.push(new HumanBeingBuilder().create((Integer) al.get(0),(String) al.get(1), new Coordinates((Long) al.get(2),(Long) al.get(3)),  (LocalDate) al.get(4), (Boolean) al.get(5), (Boolean) al.get(6), (Float) al.get(7), WeaponType.valueOf((String) al.get(8)), Mood.valueOf((String) al.get(9)), new Car((Boolean) al.get(10)), (String) al.get(11)));
            } catch (NoSuchElementException e) {
                break;
            /*} catch (ClassCastException e) {
                Server.printErr("Data type cast failed. Not all elements will appear in collection.");
            }*/}
        }
        return tmpStack;
    }
}


