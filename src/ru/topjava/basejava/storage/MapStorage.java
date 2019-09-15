package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected int getIndex(String uuid) {
        int counter = 0;
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doSave(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }
}
