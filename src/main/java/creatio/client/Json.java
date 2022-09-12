package creatio.client;

import lombok.Data;

import java.util.List;

@Data
public class Json {
    private String context;
    private List<UBMAppClient> value;
}
