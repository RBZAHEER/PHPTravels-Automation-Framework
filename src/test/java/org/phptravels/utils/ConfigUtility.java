package org.phptravels.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtility {

    private static Properties prop;

    public static String getProperties(String key) throws IOException {
        try{
            prop = new Properties();
            FileInputStream fs = new FileInputStream("src/main/resources/config.properties");
            prop.load(fs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(key);
    }
}
