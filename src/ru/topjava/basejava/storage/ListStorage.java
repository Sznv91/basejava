package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        if (storage.contains(resume)) {
            return storage.indexOf(resume);
        }
        return -1;
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doSave(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage.set(index,resume);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(int index) {
        storage.remove(index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) {
        if (storage.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
           doSave(0,resume); // 0 - not use
        }

    }

    @Override
    public Resume[] getAll() {
        Resume[] massive = new Resume[storage.size()];
        for (int i = 0; i < storage.size(); i ++){
            massive [i] = storage.get(i);
        }
        return massive;
    }
}
