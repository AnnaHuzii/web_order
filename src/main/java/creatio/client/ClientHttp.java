package creatio.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ClientHttp {

    public static final OkHttpClient CLIENT = new OkHttpClient().newBuilder()
            .build();
    private static final int RESPONCE_CODE_OK = 200;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean allClient(String urlClient, String mobilePhone) throws IOException, InterruptedException {
        String requestURL = String.format("%s?$filter=%s '%s'", urlClient, "MobilePhone eq", mobilePhone);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create("",mediaType);
        Request request = new Request.Builder()
                .url(requestURL)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("ForceUseSession", "true")
                .addHeader("BPMCSRF", "OpK/NuJJ1w/SQxmPvwNvfO")
                .build();
        Response response = CLIENT.newCall(request).execute();


        System.out.println("list.get(0) = " + response.toString());
        if (response.code() == RESPONCE_CODE_OK) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean addClient(String urlClient, UBMAppClient clientJson) throws Exception {
        String requestBody = GSON.toJson(clientJson);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(requestBody, mediaType);
        Request request = new Request.Builder()
                .url(urlClient)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("ForceUseSession", "true")
                .build();
        Response response = CLIENT.newCall(request).execute();

        if (response.code() == RESPONCE_CODE_OK) {
            return true;
        } else {
            return false;
        }
    }
}



