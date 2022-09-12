package command;

import command.settings.Command;
import connection.StorageUser;
import creatio.CreateDaoService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderMenuCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException{
        resp.setContentType("text/html; charset=utf-8");
        StorageUser util = StorageUser.getInstance();
        CreateDaoService creatioOrderDaoService = new CreateDaoService();
        String authorization = creatioOrderDaoService.authorization(util.getConnection());

        String messenger = "";
        if (authorization != null) {
            messenger = "Authorization was successful";
        } else {
            messenger = "Something went wrong did not log in to the system";
        }
//        List<String> cookies = util.getConnection();
//        for (String cookie : cookies) {
//            resp.addHeader("Set-cookie", cookie.split(";", 1)[0]);
//        }

        Context context = new Context();
        context.setVariable("Authorization", messenger);

        engine.process("orderNew", context, resp.getWriter());
        resp.getWriter().close();
    }
}
