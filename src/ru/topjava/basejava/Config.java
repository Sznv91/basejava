package ru.topjava.basejava;

import ru.topjava.basejava.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Need config TomCat param "-VM options" to -DhomeDir="PROJECT_DIR"
 * look like as -DhomeDir="C:\\topJava\\basejava"
 */

public class Config {

    private static final Config INSTANCE = new Config();
    private File STORAGE_DIR;
    private String urlDB;
    private String userDB;
    private String passwordDB;
    private SqlStorage storage;

    private Config() {
        File PROPS = new File(getHomeDir() + "/config/resumes.properties");
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(is);
            STORAGE_DIR = new File(properties.getProperty("STORAGE_DIR"));
            urlDB = properties.getProperty("db.url");
            userDB = properties.getProperty("db.user");
            passwordDB = properties.getProperty("db.password");
            storage = new SqlStorage(urlDB, userDB, passwordDB);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public static File getHomeDir() {
        String props = System.getProperty("homeDir");
        File homeDir = new File(props == null ? "." : props);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
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

    public SqlStorage getSqlStorageInstance() {
        return storage;
    }
}
