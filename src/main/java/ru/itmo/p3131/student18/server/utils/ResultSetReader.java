package ru.itmo.p3131.student18.server.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ResultSetReader {

    public String getPasswordFromLoginUser(ResultSet set) throws SQLException {
        if (set.next()) {
            return set.getString(1);
        } else { throw new NoSuchElementException("There is no user with what login."); }
    }
    public ArrayList getHumanBeingTableFromCollectionInit(ResultSet set) throws SQLException, NoSuchElementException {
        ArrayList objectFields = new ArrayList();
        if (set.next()) {
            objectFields.add(set.getInt(1));
            objectFields.add(set.getString(2));
            objectFields.add(set.getLong(3));
            objectFields.add(set.getLong(4));
            objectFields.add(set.getTimestamp(5).toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate());
            objectFields.add(set.getBoolean(6));
            objectFields.add(set.getBoolean(7));
            objectFields.add(set.getFloat(8));
            objectFields.add(set.getString(9));
            objectFields.add(set.getString(10));
            objectFields.add(set.getBoolean(11));
            objectFields.add(set.getString(12));
        } else throw new NoSuchElementException("End of ResultSet.");
        return objectFields;
    }

}
