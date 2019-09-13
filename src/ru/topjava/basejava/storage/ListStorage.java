package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.add(index,resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
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
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Collections.binarySearch(storage, resume);
    }
}
