package ru.itmo.p3131.student18.server.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private final Properties appProps = new Properties();
    private final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private final String appConfigPath = rootPath + "admin.properties";
    private String url;
    private String admin;
    private String password;

    public PropertyReader() {
        try {
            appProps.load(new FileInputStream(appConfigPath));
            url = appProps.getProperty("url");
            admin = appProps.getProperty("admin");
            password = appProps.getProperty("password");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    public String getUrl() { return url; }
    public String getAdmin() {
        return admin;
    }
    public String getPassword() {
        return password;
    }
}
