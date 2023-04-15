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
}
