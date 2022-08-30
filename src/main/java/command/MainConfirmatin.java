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
        String orderPost;
        Context context = new Context();

        String error = "Mandatory fields are not filled";
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String area = req.getParameter("area");
        String street = req.getParameter("street");
        String houseNumber = req.getParameter("house-number");
        String photo = req.getParameter("photo");
        String video = req.getParameter("video");

        Date birthDate = null;
        try {
            birthDate = Date.valueOf(LocalDate.parse(req.getParameter("birth_date")));
        }catch (Exception e){
            context.setVariable("error", error);
            engine.process("order", context, resp.getWriter());
            resp.getWriter().close();
        }

        String clientGet = "";
        if (name.equals("") || surname.equals("") || phone.equals("")) {
            context.setVariable("error", error);
            engine.process("order", context, resp.getWriter());
            resp.getWriter().close();
        } else {
            boolean MobilePhone = createOrderDaoService.MobilePhoneClient(phone);
            if (MobilePhone) {
//                String id = ;
            }else {
            UBMAppClient client = UBMAppClient.builder().
                    Name(name + " " + surname).
                    BirthDate(birthDate).
                    MobilePhone(phone)
                    .build();
                clientGet = createOrderDaoService.creatiClient(client);
        }

            System.out.println("clientGet = " + clientGet);

            UsrApplication order = UsrApplication.builder().
                    UBMAppClient(name + " " + surname).
                    UBMAppDistrict(area).
                    UBMAppSteet(street).
                    UBMAppHousenumber(houseNumber).
                    photo(photo).
                    video(video).
                    UBMAppStage("Добавлена з фото").
                    UBMAppSource("web-форма").
                    build();

//            orderPost = createOrderDaoService.createApplication(order);

            resp.setContentType("text/html; charset=utf-8");


            context.setVariable("reply", clientGet);

            engine.process("confirmation", context, resp.getWriter());
            resp.getWriter().close();
        }

    }
}