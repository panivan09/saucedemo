package saucedemo.service;


import java.util.ResourceBundle;

public class TestDataReader {

    public static final String ENV_PROPERTY = "environment";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty(ENV_PROPERTY));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
