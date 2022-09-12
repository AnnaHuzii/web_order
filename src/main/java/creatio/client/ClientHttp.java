package creatio.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.StorageUser;
import creatio.CreateDaoService;
import lombok.SneakyThrows;
import okhttp3.*;
public class ClientHttp {
    private static final CreateDaoService creatioOrderDaoService = new CreateDaoService();
    private static final StorageUser util = StorageUser.getInstance();
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().build();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final int RESPONCE_CODE_OK = 200;
    @SneakyThrows
    public static String allClient(String urlClient, String mobilePhone) {
        String requestURL = String.format("%s?$filter=%s '%s'", urlClient, "MobilePhone eq", mobilePhone);

        String authorization = creatioOrderDaoService.authorization(util.getConnection());

        Request request = new Request.Builder()
                .url(requestURL)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("ForceUseSession", "true")
                .addHeader("Cookie", authorization)
                .build();

        Response response = CLIENT.newCall(request).execute();

        if (response.code() == RESPONCE_CODE_OK) {
            String myResponse = response.body().string();
            Json json = GSON.fromJson(myResponse, Json.class);
            for (UBMAppClient client : json.getValue()) {
                return client.getId();
            }
        }
        return null;
    }

    public static String addClient(String urlClient, UBMAppClient clientJson) throws Exception {
        String authorization = creatioOrderDaoService.authorization(util.getConnection());
        String requestBody = GSON.toJson(clientJson);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(requestBody, mediaType);
        Request request = new Request.Builder()
                .url(urlClient)
                .method("POST", body)
                .addHeader("Accept", "application/json; odata=verbose")
                .addHeader("Content-Type", "application/json; odata=verbose; IEEE754Compatible=true")
                .addHeader("Cookie", authorization)
                .build();
        Response response = CLIENT.newCall(request).execute();

        if (response.code() == RESPONCE_CODE_OK) {
            String myResponse = response.body().string();
            Json json = GSON.fromJson(myResponse, Json.class);
            for (UBMAppClient client : json.getValue()) {
                return client.getId();
            }
        }
        return null;
    }
}



