package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Object indOrRes, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(Object indOrRes) {
        return storage.get((int) indOrRes);
    }

    @Override
    protected void doDelete(Object indOrRes) {
        storage.remove((int) indOrRes);
    }

    @Override
    protected void doUpdate(Object indOrRes, Resume resume) {
        storage.set((int) indOrRes, resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
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
        int counter = 0;
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    @Override
    protected boolean isExist(String uuid) {
        Resume resume = new Resume(uuid);
        if (storage.contains(resume)) {
            return true;
        } else {
            return false;
        }
    }
}
