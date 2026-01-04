package saucedemo.config;

public class EnvironmentConfig {

    private static final String ENVIRONMENT = "environment";
    private static final String DEFAULT_ENVIRONMENT = "dev";

    private EnvironmentConfig() {}

    public static String getEnvironment() {
        return System.getProperty(ENVIRONMENT, DEFAULT_ENVIRONMENT).trim();
    }
}
