package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import java.util.List;

import static java.util.Arrays.*;

/**
 * Array based storage for Resumes
 */
abstract class AbstractArrayStorage extends AbstractStorage <Integer> {

    private static final int STORAGE_LIMIT = 10_000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size;

    protected abstract void deleteFromArray(int index);

    protected abstract void pasteResume(int index, Resume resume);

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        if (size < STORAGE_LIMIT) {
            pasteResume(searchKey, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void doDelete(Integer searchKey) {
        deleteFromArray(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return asList(copyOfRange(storage, 0, size));
    }

    @Override
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}