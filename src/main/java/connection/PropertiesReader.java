package connection;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class PropertiesReader {
    private String URL_ORDEG;
    private String URL_CLIENT;

    public PropertiesReader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream reader = loader.getResourceAsStream("url.properties");

        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        URL_CLIENT =  properties.getProperty("client.connection.url");
        URL_ORDEG = properties.getProperty("order.connection.url");
    }

}
