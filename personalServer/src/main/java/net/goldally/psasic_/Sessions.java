package net.goldally.psasic_;

import net.goldally.psasic_.requests.Request;
import net.goldally.psasic_.requests.IsSession;

import java.io.IOException;
import java.net.URISyntaxException;

public class Sessions extends Request {
    public static boolean isSession(String authKey) throws IOException, URISyntaxException, InterruptedException {
        String result = HttpManipulator.send("isSession", new IsSession(authKey)).body();
        return PsasicMain.gson.fromJson(result, net.goldally.psasic_.responces.IsSession.class).state;
    }
}
