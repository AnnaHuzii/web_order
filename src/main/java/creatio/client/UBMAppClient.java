package creatio.client;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UBMAppClient {
    private String Id;
    private String Name;
    private Date BirthDate;
    private String MobilePhone;
    private String GivenName;
    private String MiddleName;
}

