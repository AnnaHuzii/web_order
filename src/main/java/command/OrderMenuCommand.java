package command;

import command.settings.Command;
import connection.StorageUser;
import creatio.CreateDaoService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderMenuCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException{
        resp.setContentType("text/html; charset=utf-8");
        StorageUser util = StorageUser.getInstance();
        CreateDaoService creatioOrderDaoService = new CreateDaoService();
        String Authorization = creatioOrderDaoService.authorization(util.getConnection());

        Context context = new Context();
        context.setVariable("Authorization", Authorization);

        engine.process("order", context, resp.getWriter());
        resp.getWriter().close();
    }
}
