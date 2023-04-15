package net.goldally.psasic_;

import net.goldally.psasic_.responces.IsSession;
import net.goldally.psasic_.responces.login;
import net.goldally.psasic_.responces.Minimal;
import net.goldally.psasic_.responces.registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;

import static net.goldally.psasic_.PsasicMain.gson;

@RestController
public class RequestProcessor {

    // Небольшая пасхалка на главной странице (При нормальных обстоятельствах её не должны открывать)
    @RequestMapping(value = "/", produces = "application/json")
    public String present() {
        return gson.toJson(new Minimal(200, "Добро пожаловать на главную страницу PSASIC API!"));
    }

    // Обработчик запросов регистрации пользователя
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(required = true) String username, @RequestParam(required = true) String password, @RequestParam(required = true) String email) throws SQLException, UnsupportedEncodingException {
        if (!Users.insert(username, password, email))
            return gson.toJson(new Minimal(406, "Имя пользователя уже занято!"));
        String authKey = Sessions.createSession(username);
        return gson.toJson(new registration(authKey));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String username, @RequestParam(required = true) String password) throws SQLException, UnsupportedEncodingException {
        if (Users.isCorrectPassowrd(username, password)) {
            Sessions.removeAllSessions(username);
            String authKey = Sessions.createSession(username);
            return gson.toJson(new login(authKey));
        }
        return gson.toJson(new Minimal(401, "Неверный логин или пароль!"));
    }

    @RequestMapping(value = "/about", method = RequestMethod.POST)
    public String about(@RequestParam(required = true) String authKey, @RequestParam(required = true) String username) throws SQLException, UnsupportedEncodingException {
        if (Sessions.isActualSession(authKey)) {
            if (Users.isThereUsersWithThisName(username)) {
                return gson.toJson(Users.about(username));
            }else{
                return gson.toJson(new Minimal(404, "Пользователь не найден!"));
            }
        }
        return gson.toJson(new Minimal(401, "Ключ сессии недействителен!"));
    }

    @RequestMapping(value = "/quit", method = RequestMethod.POST)
    public String quit(@RequestParam(required = true) String authKey) throws SQLException, UnsupportedEncodingException {
        Sessions.endSession(authKey);
        return gson.toJson(new Minimal(200, "Сессия завершена!"));
    }

    @RequestMapping(value = "/isSession", method = RequestMethod.POST)
    public String isSession(@RequestParam(required = true) String authKey) throws SQLException, UnsupportedEncodingException {
        return gson.toJson(new IsSession(Sessions.isActualSession(authKey)));
    }

    @RequestMapping(value = "/changeMyPersonalData", method = RequestMethod.POST)
    public String changeData(@RequestParam(required = true) String authKey,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String surname,
                             @RequestParam(required = false) Date dateOfBirth,
                             @RequestParam(required = false) String icon)
            throws SQLException, UnsupportedEncodingException {
        String username = Users.getUserBySession(authKey);
        if(name != null){
            Users.setName(username, name);
        }
        if(surname != null){
            Users.setSurname(username, surname);
        }
        if(dateOfBirth != null){
            Users.setDateOfBirth(username, dateOfBirth);
        }
        if(icon != null){
            Users.setIcon(username, icon);
        }
        return gson.toJson(new Minimal(200, "OK"));
    }
}
