package ru.topjava.basejava;

import java.io.*;
import java.util.Properties;

public class Config {

    private static final Config INSTANCE = new Config();
    private File STORAGE_DIR;
    private String urlDB;
    private String userDB;
    private String passwordDB;

    private Config() {
        File PROPS = new File("./config/resumes.properties");
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(is);
            STORAGE_DIR = new File(properties.getProperty("STORAGE_DIR"));
            urlDB = properties.getProperty("db.url");
            userDB = properties.getProperty("db.user");
            passwordDB = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getSTORAGE_DIR() {
        return STORAGE_DIR;
    }

    public String getUrlDB() {
        return urlDB;
    }

    public String getUserDB() {
        return userDB;
    }

    public String getPasswordDB() {
        return passwordDB;
    }

    public static Config getInstance() {
        return INSTANCE;
    }
}
