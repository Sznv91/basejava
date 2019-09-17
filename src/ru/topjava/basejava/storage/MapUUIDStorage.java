package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage {

    protected final Map storage;

    public MapUUIDStorage() {
        this(new HashMap<String, Resume>());
    }

    public MapUUIDStorage(Map type) {
        this.storage = type;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) storage.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage.replace(searchKey, resume);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>(storage.values());
        result.sort((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
        return result;
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
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }
}