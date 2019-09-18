package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

/**
 * Array based storage for Resumes
 */
abstract class AbstractArrayStorage extends AbstractStorage {

    private static final int STORAGE_LIMIT = 10_000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void deleteFromArray(int index);

    protected abstract void pasteResume(int index, Resume resume);

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        if (size < STORAGE_LIMIT) {
            pasteResume((int) searchKey, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteFromArray((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return new ArrayList<>(asList(copyOfRange(storage, 0, size)));
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
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }
}