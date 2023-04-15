package net.goldally.psasic_;

import net.goldally.psasic_.responces.Minimal;
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
        return gson.toJson(new Minimal(200, "Добро пожаловать на главную страницу персонального сервера PSASIC API!"));
    }
}
