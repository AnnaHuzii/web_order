package command.settings;

import command.MainConfirmatin;

import command.OrderMenuCommand;
import command.main.ChooseTableCommand;
import command.main.MainMenuCommand;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private final Map<String, Command> commands;

    public CommandService() {
        commands = new HashMap<>();

        commands.put("GET /", new MainMenuCommand());
        commands.put("GET /orderNew", new OrderMenuCommand());
        commands.put("GET /confirmation", new MainConfirmatin());
        commands.put("POST /", new ChooseTableCommand());
    }

    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException, SQLException {

        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;

        commands.get(commandKey).process(req, resp, engine);
    }

}
