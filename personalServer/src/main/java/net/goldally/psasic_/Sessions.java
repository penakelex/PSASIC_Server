package net.goldally.psasic_;

import net.goldally.psasic_.requests.Request;
import net.goldally.psasic_.responces.IsSession;
import net.goldally.psasic_.responces.userBySession;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Sessions extends Request {
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
        data = httpManipulator.send("userBySession", params);
        userBySession result2 = PsasicMain.gson.fromJson(data, userBySession.class);
        if (result2.code != 200) {
            throw new IOException("Ошибка взаимодействия с сервером: " + data);
        }
        DataBaseControl.isMemberStatement.setString(1, result2.username);
        if (!DataBaseControl.isMemberStatement.executeQuery().getBoolean(1)) {
            DataBaseControl.addMemberStatement.setString(1, result2.username);
            DataBaseControl.addMemberStatement.execute();
        }
        return true;
    }
}
