package creatio.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.StorageUser;
import creatio.CreateDaoService;
import okhttp3.*;
import java.io.IOException;

public class OrderHttp {
    private static final CreateDaoService creatioOrderDaoService = new CreateDaoService();
    private static final StorageUser util = StorageUser.getInstance();
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().build();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final int RESPONCE_CODE_OK = 200;

    public static boolean sendingOrder(String url, Object order) throws IOException {
        String authorization = creatioOrderDaoService.authorization(util.getConnection());

        String requestBody = GSON.toJson(order);
        MediaType mediaType = MediaType.parse("application/json; odata=verbose; IEEE754Compatible=true");
        RequestBody body = RequestBody.create(requestBody, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Accept", "application/json; odata=verbose")
                .addHeader("Content-Type", "application/json; odata=verbose; IEEE754Compatible=true")
                .addHeader("Cookie", authorization)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response.code() == RESPONCE_CODE_OK;
    }
}



