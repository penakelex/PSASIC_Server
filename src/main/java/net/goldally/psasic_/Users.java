package net.goldally.psasic_;

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

    public static Boolean insert(String name, String password) throws SQLException {
        if (!isThereUsersWithThisName(name)) {
            DataBaseControl.UserInsertStatement.setString(1, name);
            DataBaseControl.UserInsertStatement.setString(2, password);
            DataBaseControl.UserInsertStatement.execute();
            return true;
        }
        return false;
    }
}
