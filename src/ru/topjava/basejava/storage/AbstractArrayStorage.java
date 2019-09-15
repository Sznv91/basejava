package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
abstract class AbstractArrayStorage extends AbstractStorage {

    private static final int STORAGE_LIMIT = 10_000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void deleteFromArray(int index);

    protected abstract void pasteResume(int index, Resume resume);

    protected void doSave(int index, Resume resume) {
        if (size < STORAGE_LIMIT) {
            pasteResume(index, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return storage[index];
    }

    @Override
    protected void doDelete(int index, String uuid) {
        deleteFromArray(index);
        storage[size - 1] = null;
        size--;
    }

    protected void doUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

}