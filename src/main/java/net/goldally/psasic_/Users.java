package net.goldally.psasic_;

import net.goldally.psasic_.responces.UserInfo;

import javax.xml.crypto.Data;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    public static boolean isThereUsersWithThisName(String name) throws SQLException {
        DataBaseControl.UserCountStatement.setString(1, name);
        ResultSet res = DataBaseControl.UserCountStatement.executeQuery();
        int usersWithThisUsername = res.getInt(1);
        return usersWithThisUsername > 0;
    }

    public static Boolean insert(String username, String password) throws SQLException {
        if (!isThereUsersWithThisName(username)) {
            DataBaseControl.UserInsertStatement.setString(1, username);
            DataBaseControl.UserInsertStatement.setString(2, password);
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
}
