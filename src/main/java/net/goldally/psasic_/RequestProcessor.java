package net.goldally.psasic_;

import net.goldally.psasic_.responces.minimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static net.goldally.psasic_.PsasicMain.gson;

@RestController
public class RequestProcessor {

    // Небольшая пасхалка на главной странице (При нормальных обстоятельствах её не должны открывать)
    @RequestMapping(value = "/", produces = "application/json")
    public String present() {
        return gson.toJson(new minimal(200, "Добро пожаловать на главную страницу PSASIC API!"));
    }

    // Обработчик запросов регистрации пользователя
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(required = true) String name, @RequestParam(required = true) String password) throws SQLException {
        if (!Users.insert(name, password))
            return gson.toJson(new minimal(406, "Имя пользователя уже занято!"));
        return gson.toJson(new minimal(200, "Новый пользователь зарегистрирован!"));
    }
}
