package command.main;

import command.settings.Command;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseTableCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        Context context = new Context();
        context.setVariable("table", req.getParameter("table"));
        switch(req.getParameter("table")) {
            case "newOrder":
                resp.sendRedirect(req.getRequestURI() + "orderNew");
                break;
            case "orderReceipt":
                resp.sendRedirect(req.getRequestURI() + "order");
                break;
        }
    }
}
