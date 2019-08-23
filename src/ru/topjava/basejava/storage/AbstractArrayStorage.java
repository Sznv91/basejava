package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
abstract class AbstractArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10_000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    protected abstract void doSave(int index, Resume resume);

    protected abstract void doDelete(int index);

    protected abstract int getIndex(String uuid);

    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (size == 0) {
                storage[0] = resume;
                size++;
                return;
            }
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                doSave(index, resume);
                size++;
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
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