package com.webapp.util;

import com.webapp.storage.SqlStorage;
import com.webapp.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final String PROPS = "/resumes.properties";
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final Storage storage;

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            Properties properties = new Properties();
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));
            System.out.println(storageDir.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPS);
        }
    }

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }
}
