package net.goldally.psasic_;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;


@SpringBootApplication
@RestController
public class PsasicMain implements ErrorController {
    public static Gson gson = new Gson();
    public static final Logger logger = LoggerFactory.getLogger("Профилировщание");

    // Инициализация.
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, URISyntaxException, InterruptedException {
        SpringApplication.run(PsasicMain.class, args);

        // Указываем на важность русского языка.
        logger.info("");
        logger.warn("С ЭТОГО МОМЕНТА БУДЕМ ГОВОРИТЬ ПО-РУССКИ.");
        DataBaseControl.init();
        logger.info("Подключение к базе данных выполнено успешно!");
    }
}
