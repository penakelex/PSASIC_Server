package net.goldally.psasic_;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import net.goldally.psasic_.responces.Minimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static net.goldally.psasic_.PsasicMain.gson;
import static net.goldally.psasic_.PsasicMain.log;

@RestController
public class ErrorProcessor {
    @RequestMapping(value = "/error", produces = "application/json")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        log.info("");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            return gson.toJson(new Minimal(statusCode, message.toString()));
        }
        return gson.toJson(new Minimal(404, "Вы только что вручную открыли страницу ошибки... Замечательно."));
    }
}
