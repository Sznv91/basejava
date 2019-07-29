package ru.topjava.basejava.storage;

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
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                doSave(index, resume);
                size++;
            } else {
                System.out.println("Not saved, uuid already exist");
            }
        } else {
            System.out.println(" not enough free cells in storage");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            size--;
        } else {
            System.out.println("Not delete, UUID " + uuid + " not found");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Not updated, UUID " + resume.getUuid() + " not found");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume \"" + uuid + "\" doesn't found in massive");
        return null;
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