package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doSave(Object indOrRes, Resume resume) {
        storage.put((String) indOrRes, resume);
    }

    @Override
    protected Resume doGet(Object indOrRes) {
        return storage.get(indOrRes);
    }

    @Override
    protected void doDelete(Object indOrRes) {
        storage.remove(indOrRes);
    }

    @Override
    protected void doUpdate(Object indOrRes, Resume resume) {
        storage.replace((String) indOrRes, resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        if (storage.containsKey(uuid)) {
            return true;
        } else {
            return false;
        }
    }
}
