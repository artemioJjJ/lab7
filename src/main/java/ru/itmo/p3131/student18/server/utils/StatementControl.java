package ru.itmo.p3131.student18.server.utils;

import org.postgresql.util.PSQLException;
import ru.itmo.p3131.student18.interim.objectclasses.Mood;
import ru.itmo.p3131.student18.interim.objectclasses.WeaponType;

import java.sql.*;
import java.util.ResourceBundle;

public class StatementControl {
    private Connection connection;
    private final String COLLECTION_INIT = "SELECT * FROM \"human_beings\"";
    private final String ADD_NEW_OBJECT = "insert into \"human_beings\" (_name, coordinates_x, coordinates_y, real_hero, has_toothpick, impact_speed, weapon_type, mood, car, user_id) VALUES (?,?,?,?,?,?,?,?,?,(select user_id from users where login = ?)) ";
    private final String REMOVE_OBJECT_BY_ID = "DELETE FROM \"human_beings\" WHERE id=? and user_id= (select user_id from \"users\" where login=?)";
    private final String REMOVE_FIRST = "DELETE FROM \"human_beings\" WHERE id=min(id) and user_id= (select user_id from \"users\" where login=?)";
    private final String REMOVE_LAST = "DELETE FROM \"human_beings\" WHERE id=max(id) and user_id= (select user_id from \"users\" where login=?)";
    private final String REMOVE_GREATER_THAN = "DELETE FROM \"human_beings\" WHERE id > ? and user_id= (select user_id from \"users\" where login=?)";
    private final String CLEAR_BY_USERNAME = "DELETE FROM \"human_beings\" WHERE user_id = (SELECT user_id from \"users\" where login = ?)";
    private final String UPDATE_BY_ID = "UPDATE \"human_beings\" SET _name=?,coordinates_x=?,coordinates_y=?,real_hero=?,has_toothpick=?,impact_speed=?,weapon_type=?,mood=?,car=? WHERE user_id=(select user_id from users where login = ?) and id=?";
    private final String REGISTER_USER = "insert into \"users\" (login, password) VALUES (?,?)";
    private final String LOGIN_USER = "SELECT \"password\" FROM users WHERE login=?";

    public void startConnection(String url,String userName,String userPassword) throws SQLException {
        connection = DriverManager.getConnection(url,userName,userPassword);
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
    public ResultSet collectionInit() throws SQLException {
        var ps = connection.prepareCall(COLLECTION_INIT);
        ps.execute();
        return ps.getResultSet();
    }
    public void addNewObject(String name, long coordinates_x, long coordinates_y, boolean realHero, boolean hasToothPick, double impactSpeed, WeaponType weaponType, Mood mood, boolean isCarCool, String user) throws SQLException, PSQLException {
         PreparedStatement ps = connection.prepareCall(ADD_NEW_OBJECT);
         setParams(ps, name, coordinates_x, coordinates_y, realHero, hasToothPick, impactSpeed, weaponType, mood, isCarCool, user);
         ps.execute();
         ps.close();
     }
    public void updateObjectById(int id, String name, long coordinates_x, long coordinates_y, boolean realHero, boolean hasToothPick, double impactSpeed, WeaponType weaponType, Mood mood, boolean isCarCool, String user) throws SQLException, PSQLException {
         PreparedStatement ps = connection.prepareCall(UPDATE_BY_ID);
         ps.setInt(11, id);
         setParams(ps, name, coordinates_x, coordinates_y, realHero, hasToothPick, impactSpeed, weaponType, mood, isCarCool, user);
         ps.execute();
         ps.close();
    }
    private void setParams(PreparedStatement ps, String name, long coordinates_x, long coordinates_y, boolean realHero, boolean hasToothPick, double impactSpeed, WeaponType weaponType, Mood mood, boolean isCarCool, String user) throws SQLException {
         ps.setString(1, name);
         ps.setLong(2, coordinates_x);
         ps.setLong(3, coordinates_y);
         ps.setBoolean(4, realHero);
         ps.setBoolean(5, hasToothPick);
         ps.setDouble(6, impactSpeed);
         ps.setString(7, weaponType.toString());
         ps.setString(8, mood.toString());
         ps.setBoolean(9, isCarCool);
         ps.setString(10, user);
    }
    public void clearByUserName(String user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CLEAR_BY_USERNAME);
        ps.setString(1, user);
        ps.execute();
        ps.close();
    }
    public void removeObjectById(int id, String user) throws SQLException {
         PreparedStatement ps = connection.prepareCall(REMOVE_OBJECT_BY_ID);
         ps.setInt(1,id);
         ps.setString(2, user);
         ps.execute();
         ps.close();
    }
    public void removeFirst(String user) throws SQLException {
        PreparedStatement ps = connection.prepareCall(REMOVE_FIRST);
        ps.setString(1, user);
        ps.execute();
        ps.close();
    }
    public void removeLast(String user) throws SQLException {
        PreparedStatement ps = connection.prepareCall(REMOVE_LAST);
        ps.setString(1, user);
        ps.execute();
        ps.close();
    }
    public void removeGreaterThan(int id, String user) throws SQLException {
        PreparedStatement ps = connection.prepareCall(REMOVE_GREATER_THAN);
        ps.setInt(1, id);
        ps.setString(2, user);
        ps.execute();
        ps.close();
    }
    public void registerUser(String login, String password) throws SQLException {
        PreparedStatement ps = connection.prepareCall(REGISTER_USER);
        ps.setString(1, login);
        ps.setString(2, password);
        ps.execute();
        ps.close();
    }
    public ResultSet loginUser(String login) throws SQLException {
        PreparedStatement ps = connection.prepareCall(LOGIN_USER);
        ps.setString(1, login);
        ps.execute();
        return ps.getResultSet();
    }



}
