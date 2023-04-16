package net.goldally.psasic_;

import net.goldally.psasic_.misc.GEO;
import net.goldally.psasic_.responces.*;
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
        if (Sessions.validate(authKey)) {
            return gson.toJson(new UsersList(Users.getAllUsers()));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/addFriend", produces = "application/json")
    public String addFriend(@RequestParam(required = true) String authKey, @RequestParam(required = true) String username) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            int state = Users.addFriend(authKey, username);
            return gson.toJson(new AddFriend(state));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/removeFriend", produces = "application/json")
    public String removeFriend(@RequestParam(required = true) String authKey, @RequestParam(required = true) String username) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            Users.removeFriend(authKey, username);
            return gson.toJson(new Minimal(200, "OK"));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/friends", produces = "application/json")
    public String listFriends(@RequestParam(required = true) String authKey) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            return gson.toJson(new FriendsList(Users.getFriends(authKey)));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/setGeo", produces = "application/json")
    public String setGeo(@RequestParam(required = true) String authKey, @RequestParam(required = true) double altitude, @RequestParam(required = true) double longitude) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            Users.setGeo(authKey, altitude, longitude);
            return gson.toJson(new Minimal(200, "OK"));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/getGeo", produces = "application/json")
    public String GetGeo(@RequestParam(required = true) String authKey, @RequestParam(required = true) String username) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            GEO geo = Users.getGeo(username);
            return gson.toJson(new GetGeo(geo.latitude, geo.longitude));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/sendMessage", produces = "application/json")
    public String sendMessage(@RequestParam(required = true) String authKey, @RequestParam(required = true) String username, @RequestParam(required = true) String message) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            return gson.toJson(new SendMessage(Users.sendMessage(authKey, username, message)));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/getMessages", produces = "application/json")
    public String getMessages(@RequestParam(required = true) String authKey, @RequestParam(required = true) String username) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            return gson.toJson(new GetMessages(Users.getMessages(authKey, username)));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }

    @RequestMapping(value = "/newMessages", produces = "application/json")
    public String newMessages(@RequestParam(required = true) String authKey) throws IOException, URISyntaxException, InterruptedException, SQLException {
        if (Sessions.validate(authKey)) {
            return gson.toJson(new GetMessages(Users.getNewMessages(authKey)));
        } else {
            return gson.toJson(new Minimal(406, "Ваша сессия недействительна!"));
        }
    }
}
