package net.goldally.psasic_;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;


@SpringBootApplication
@RestController
public class PsasicMain implements ErrorController {
    public static final Logger log = LoggerFactory.getLogger("Профилировщание");

    // Инициализация.
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        SpringApplication.run(PsasicMain.class, args);

        // Указываем на важность русского языка.
        log.info("");
        log.warn("С ЭТОГО МОМЕНТА БУДЕМ ГОВОРИТЬ ПО-РУССКИ.");
        DataBaseControl.init();
        log.info("Подключение к базе данных выполнено успешно!");

    }
}
