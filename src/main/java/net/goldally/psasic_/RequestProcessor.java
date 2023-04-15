package net.goldally.psasic_;

import net.goldally.psasic_.misc.Formater;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class RequestProcessor {

    // Небольшая пасхалка на главной странице (При нормальных обстоятельствах её не должны открывать)
    @RequestMapping(value = "/", produces = "application/json")
    public String present() {
        return Formater.sampleResponse(200, "Добро пожаловать на главную страницу PSASIC API!");
    }

    // Обработчик запросов регистрации пользователя
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String home(@RequestParam(required = true) String name, @RequestParam(required = true) String password) throws SQLException {
        if (!Users.insert(name, password))
            return Formater.sampleResponse(406, "Имя пользователя уже занято!");
        return Formater.sampleResponse(200, "Новый пользователь зарегистрирован!");
    }
}
