package net.goldally.psasic_;

import net.goldally.psasic_.responces.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Users {
    public static boolean isThereUsersWithThisName(String name) throws SQLException {
        DataBaseControl.UserCountStatement.setString(1, name);
        ResultSet res = DataBaseControl.UserCountStatement.executeQuery();
        int usersWithThisUsername = res.getInt(1);
        return usersWithThisUsername > 0;
    }

    public static Boolean insert(String username, String password, String email) throws SQLException {
        if (!isThereUsersWithThisName(username)) {
            DataBaseControl.UserInsertStatement.setString(1, username);
            DataBaseControl.UserInsertStatement.setString(2, password);
            DataBaseControl.UserInsertStatement.setString(3, email);
            DataBaseControl.UserInsertStatement.execute();
            return true;
        }
        return false;
    }

    public static boolean isCorrectPassowrd(String username, String password) throws SQLException {
        DataBaseControl.isUserPasswordStatement.setString(1, username);
        DataBaseControl.isUserPasswordStatement.setString(2, password);
        ResultSet res = DataBaseControl.isUserPasswordStatement.executeQuery();
        return res.getBoolean(1);
    }

    public static Object about(String username) throws SQLException {
        DataBaseControl.aboutStatement.setString(1, username);
        ResultSet res = DataBaseControl.aboutStatement.executeQuery();
        return new UserInfo(res.getString("username"),
                res.getString("name"),
                res.getString("surname"),
                res.getDate("dateOfBirth"),
                res.getString("icon"));
    }

    public static String getUserBySession(String authKey) throws SQLException {
        DataBaseControl.userBySessionStatement.setString(1, authKey);
        return DataBaseControl.userBySessionStatement.executeQuery().getString(1);
    }

    public static void setName(String username, String name) throws SQLException {
        DataBaseControl.changeNameStatement.setString(1, name);
        DataBaseControl.changeNameStatement.setString(2, username);
        DataBaseControl.changeNameStatement.execute();
    }

    public static void setSurname(String username, String surname) throws SQLException {
        DataBaseControl.changeSurnameStatement.setString(1, surname);
        DataBaseControl.changeSurnameStatement.setString(2, username);
        DataBaseControl.changeSurnameStatement.execute();
    }

    public static void setDateOfBirth(String username, Date date) throws SQLException {
        DataBaseControl.changeDateOfBirthStatement.setDate(1, new java.sql.Date(date.getTime()));
        DataBaseControl.changeDateOfBirthStatement.setString(2, username);
        DataBaseControl.changeDateOfBirthStatement.execute();
    }

    public static void setIcon(String username, String icon) throws SQLException {
        DataBaseControl.changeIconStatement.setString(1, icon);
        DataBaseControl.changeIconStatement.setString(2, username);
        DataBaseControl.changeIconStatement.execute();
    }
}
