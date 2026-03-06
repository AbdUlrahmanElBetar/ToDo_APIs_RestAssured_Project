package com.qacart.todo.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String getBaseUrl() {
        String env = System.getProperty("env", "PRODUCTION");


        Properties properties = new Properties();

        try (InputStream input = Config.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties file not found");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }

        String baseUrl = properties.getProperty(env);

        if (baseUrl == null) {
            throw new RuntimeException("Environment not supported: " + env);
        }

        return baseUrl;
    }

}