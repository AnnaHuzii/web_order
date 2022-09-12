package creatio;

import connection.PropertiesReader;
import creatio.client.ClientHttp;
import creatio.client.UBMAppClient;
import creatio.order.OrderHttp;
import creatio.order.UsrApplication;

import java.util.List;
import java.util.StringJoiner;

public class CreateDaoService {
    public String MobilePhoneClient(String mobilePhone) {
        String replyClient = "";
        try {
            PropertiesReader propertiesReader = new PropertiesReader();
            String url = propertiesReader.getURL_CLIENT();
            replyClient = ClientHttp.allClient(url, mobilePhone);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return replyClient;
    }

    public String creatiClient(UBMAppClient client) {
        String replyClient = "";
        try {
            PropertiesReader propertiesReader = new PropertiesReader();
            String url = propertiesReader.getURL_CLIENT();
            UBMAppClient clientConnection = appClient(client);
            replyClient = ClientHttp.addClient(url, clientConnection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return replyClient;
    }

    public String createApplication(UsrApplication order, UBMAppClient client) {

        String id = MobilePhoneClient(client.getMobilePhone());
        if (id.equals("")) {
            id = creatiClient(client);
        }

        boolean connection = true;

        try {

            PropertiesReader propertiesReaderOrder = new PropertiesReader();
            String url = propertiesReaderOrder.getURL_ORDEG();

            UsrApplication orderConnection = createOrder(order, id);
            connection = OrderHttp.sendingOrder(url, orderConnection);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (connection) {
            return "The order has been sent successfully";
        } else {
            return "Your order could not be sent, there was a problem, please try again";
        }
    }

    public String authorization(List<String> cookies) {

        StringJoiner resultCookie = new StringJoiner(";");
        for (String cookie : cookies) {
            resultCookie.add(cookie.split(";", 2)[0]);
        }
        return resultCookie.toString();
    }

    public static UBMAppClient appClient(UBMAppClient client) {
        return UBMAppClient.builder().
                Name(client.getName()).
                BirthDate(client.getBirthDate()).
                MobilePhone(client.getMobilePhone())
                .build();
    }

    public static UsrApplication createOrder(UsrApplication order, String id) {

        return UsrApplication.builder().
                UBMAppClientId(id).
                UBMAppClient(order.getUBMAppClient()).
                UBMAppSteet(order.getUBMAppSteet()).
                UBMAppStage(order.getUBMAppStage()).
                UBMAppHousenumber(order.getUBMAppHousenumber()).
                photo(order.getPhoto()).
                video(order.getVideo()).
                UBMAppSource(order.getUBMAppSource()).
                UBMAppStage(order.getUBMAppStage()).
                build();
    }

}
