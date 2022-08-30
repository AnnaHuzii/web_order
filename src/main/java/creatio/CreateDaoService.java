package creatio;

import connection.PropertiesReader;
import creatio.client.ClientHttp;
import creatio.client.UBMAppClient;
import creatio.order.OrderHttp;
import creatio.order.UsrApplication;

public class CreateDaoService {

    public boolean MobilePhoneClient(String mobilePhone) {
        boolean replyClient = true;
        try {

            PropertiesReader propertiesReader = new PropertiesReader();
            String url = propertiesReader.getURL_CLIENT();
            replyClient = ClientHttp.allClient(url, mobilePhone);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (replyClient) {
            return true;
        } else {
            return false;
        }
    }

    public String creatiClient(UBMAppClient client) {
        boolean replyClient = true;
        try {

            PropertiesReader propertiesReader = new PropertiesReader();
            String url = propertiesReader.getURL_CLIENT();

            UBMAppClient clientConnection = appClient(client);
            replyClient = ClientHttp.addClient(url, clientConnection);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (replyClient) {
            return "The order has been sent successfully";
        } else {
            return "Your order could not be sent, there was a problem, please try again";
        }
    }

    public String createApplication(UsrApplication order) {
        boolean connection = true;
        try {

            PropertiesReader propertiesReaderOrder = new PropertiesReader();
            String url = propertiesReaderOrder.getURL_ORDEG();

            UsrApplication orderConnection = createOrder(order);
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
    public String authorization(boolean connection) {
        String result = "";
        if (connection) {
            result = "Authorization was successful";
        } else {
            result = "Something went wrong did not log in to the system";
        }
        return result;
    }

    public static UBMAppClient appClient(UBMAppClient client) {
        return UBMAppClient.builder().
                Name(client.getName()).
                BirthDate(client.getBirthDate()).
                MobilePhone(client.getMobilePhone())
                .build();
    }

    public static UsrApplication createOrder(UsrApplication order) {

        return UsrApplication.builder().
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
