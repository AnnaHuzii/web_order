package creatio.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserHttp {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final int RESPONCE_CODE_OK = 200;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean logsUser(String url, Object login) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(login);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json; odata=verbose; IEEE754Compatible=true");
        RequestBody body = RequestBody.create(requestBody, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json; odata=verbose")
                .addHeader("Content-Type", "application/json; odata=verbose; IEEE754Compatible=true")
                .addHeader("BPMCSRF", "OpK/NuJJ1w/SQxmPvwNvfO")
                .build();
        Response response = client.newCall(request).execute();

        if (response.code() == RESPONCE_CODE_OK) {
            return true;
        } else {
            return false;
        }
    }


}



