package creatio.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrderHttp {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final int RESPONCE_CODE_OK = 200;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean sendingOrder(String url, Object order) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(order);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (responce.statusCode() == RESPONCE_CODE_OK) {
            return true;
        } else {
            return false;
        }
    }


}



