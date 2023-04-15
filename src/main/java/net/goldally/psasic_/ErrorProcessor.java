package net.goldally.psasic_;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import net.goldally.psasic_.responces.minimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static net.goldally.psasic_.PsasicMain.gson;

@RestController
public class ErrorProcessor {
    @RequestMapping(value = "/error", produces = "application/json")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            return gson.toJson(new minimal(statusCode, message.toString()));
        }
        return gson.toJson(new minimal(404, "Вы только что вручную открыли страницу ошибки... Замечательно."));
    }
}
