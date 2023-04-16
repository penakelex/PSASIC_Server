package net.goldally.psasic_;

import net.goldally.psasic_.responces.FriendsList;
import net.goldally.psasic_.responces.Minimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static net.goldally.psasic_.PsasicMain.gson;

@RestController
public class RequestProcessor {

    // Небольшая пасхалка на главной странице (При нормальных обстоятельствах её не должны открывать)
    @RequestMapping(value = "/", produces = "application/json")
    public String present() {
        return gson.toJson(new Minimal(200, "Добро пожаловать на главную страницу персонального сервера PSASIC API!"));
    }

    @RequestMapping(value = "/listUsers", produces = "application/json")
    public String listUsers(@RequestParam(required = true) String authKey) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.isSession(authKey)) {
            return gson.toJson(new FriendsList(Users.getAllUsers()));
        }else{
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }
}
