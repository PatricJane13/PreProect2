package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperties() {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = cl.getResourceAsStream("conn.properties");
            Properties properties = new Properties();
            assert inputStream != null;
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
