package creatio.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsrApplication {
    private String UBMAppClient;
    private String UBMAppDistrict;
    private String UBMAppSteet;
    private String UBMAppHousenumber;
    private String photo;
    private String video;
    private String UBMAppSource;
    private String UBMAppStage;

}
