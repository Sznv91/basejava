package ru.topjava.basejava.web;

import ru.topjava.basejava.Config;
import ru.topjava.basejava.storage.SqlStorage;

public class SqlStorageInstance {

    private static SqlStorageInstance instance;
    private SqlStorage storage;

    private SqlStorageInstance() {
        storage = new SqlStorage(Config.getInstance().getUrlDB(), Config.getInstance().getUserDB(), Config.getInstance().getPasswordDB());
    }

    public static SqlStorageInstance getInstance() {
        if (instance == null) {
            instance = new SqlStorageInstance();
        }
        return instance;
    }

    public SqlStorage getSqlStorageInstance() {
        return storage;
    }
}


