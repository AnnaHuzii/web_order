package command;

import command.settings.Command;
import creatio.client.UBMAppClient;
import creatio.CreateDaoService;
import creatio.order.UsrApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class MainConfirmatin implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        CreateDaoService createOrderDaoService = new CreateDaoService();
        Context context = new Context();

        String error = "Mandatory fields are not filled";
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String area = req.getParameter("area");
        String street = req.getParameter("street");
        String houseNumber = req.getParameter("house-number");

        Date birthDate = null;
        try {
            birthDate = Date.valueOf(LocalDate.parse(req.getParameter("birth_date")));
        } catch (Exception e) {
            context.setVariable("error", error);
            engine.process("orderNew", context, resp.getWriter());
            resp.getWriter().close();
        }

        String messenger = "";
        if (name.equals("") || surname.equals("") || phone.equals("")) {
            context.setVariable("error", error);
            engine.process("orderNew", context, resp.getWriter());
            resp.getWriter().close();
        } else {
            UsrApplication order = UsrApplication.builder().
                    UBMAppClient(name + " " + surname).
                    UBMAppDistrict(area).
                    UBMAppSteet(street).
                    UBMAppHousenumber(houseNumber).
                    UBMAppStage("Нова").
                    UBMAppSource("web-форма").
                    build();

            UBMAppClient client = UBMAppClient.builder().
                    Name(name + " " + surname).
                    BirthDate(birthDate).
                    MobilePhone(phone).
                    GivenName(name).
                    MiddleName(surname).
                    build();

            messenger = createOrderDaoService.createApplication(order,client);
        }
        resp.setContentType("text/html; charset=utf-8");

        context.setVariable("reply", messenger);

        engine.process("confirmation", context, resp.getWriter());
        resp.getWriter().close();
    }

}
