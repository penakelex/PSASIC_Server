package net.goldally.psasic_;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import static net.goldally.psasic_.misc.AuthKeyGenerator.generateAuthKey;

public class Sessions {
    public static String createSession(String username) throws UnsupportedEncodingException, SQLException {
        String authKey = generateAuthKey();
        DataBaseControl.createSessionStatement.setString(1, username);
        DataBaseControl.createSessionStatement.setString(2, authKey);
        DataBaseControl.createSessionStatement.execute();
        return authKey;
    }

    public static void removeAllSessions(String username) throws UnsupportedEncodingException, SQLException {
        DataBaseControl.removeAllSessionsStatement.setString(1, username);
        DataBaseControl.removeAllSessionsStatement.execute();
    }

    public static boolean isActualSession(String session) throws SQLException {
        DataBaseControl.isActualSessionStatement.setString(1, session);
        return DataBaseControl.isActualSessionStatement.executeQuery().getBoolean(1);
    }

    public static void endSession(String authKey) throws SQLException {
        DataBaseControl.removeSessionStatement.setString(1, authKey);
        DataBaseControl.removeSessionStatement.execute();
    }

    public static String userBySession(String authKey) throws SQLException {
        DataBaseControl.userBySessionStatement.setString(1, authKey);
        return DataBaseControl.userBySessionStatement.executeQuery().getString(1);
    }
}
