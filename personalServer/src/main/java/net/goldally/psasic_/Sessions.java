package net.goldally.psasic_;

import net.goldally.psasic_.requests.Request;
import net.goldally.psasic_.responces.IsSession;
import net.goldally.psasic_.responces.UserBySession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Sessions extends Request {

    public static String userBySession(String authKey) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("authKey", authKey);
        String data = httpManipulator.send("userBySession", params);
        UserBySession result = PsasicMain.gson.fromJson(data, UserBySession.class);
        if(result.code != 200){
            throw new IOException("Ошибка взаимодействия с сервером: " + data);
        }
        return result.username;
    }
    public static boolean validate(String authKey) throws IOException, SQLException {
        Map<String, String> params = new HashMap<>();
        params.put("authKey", authKey);
        String data = httpManipulator.send("isSession", params);
        IsSession result = PsasicMain.gson.fromJson(data, IsSession.class);
        if (result.code != 200) {
            throw new IOException("Ошибка взаимодействия с сервером: " + data);
        }
        if(!result.state){
            return false;
        }
        String username = userBySession(authKey);
        DataBaseControl.isMemberStatement.setString(1, username);
        if (!DataBaseControl.isMemberStatement.executeQuery().getBoolean(1)) {
            DataBaseControl.addMemberStatement.setString(1, username);
            DataBaseControl.addMemberStatement.execute();
        }
        return true;
    }
}
