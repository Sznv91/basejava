package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage.set((int) searchKey, resume);
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
    protected Object searchKey(String uuid) {
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
        return storage.contains(resume);
    }
}
