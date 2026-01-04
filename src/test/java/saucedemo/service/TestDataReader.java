package saucedemo.service;


import saucedemo.config.EnvironmentConfig;

import java.util.ResourceBundle;

public class TestDataReader {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(EnvironmentConfig.getEnvironment());

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
