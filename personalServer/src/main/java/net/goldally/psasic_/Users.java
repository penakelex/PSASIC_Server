package net.goldally.psasic_;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {
    public static List<String> getAllUsers() throws SQLException {
        ResultSet a = DataBaseControl.listUsersStatement.executeQuery();
        List<String> usernames = new ArrayList<>();
        while(a.next()){
            usernames.add(a.getString(1));
        }
        return usernames;
    }
}
