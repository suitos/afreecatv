package main.java.com.afreecatv.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static Properties configFile;

    static {
        configFile = new Properties();
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("config.properties 파일을 찾을 수 없습니다.");
            }
            configFile.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
    }

}
