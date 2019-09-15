package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object searchKey(String uuid);

    public abstract int size();

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract boolean isExist(String uuid);

    @Override
    public void save(Resume resume) {
        if (isExist(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(searchKey(resume.getUuid()), resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(searchKey(uuid));
        }
    }

    @Override
    public void delete(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchKey(uuid));
        }
    }

    @Override
    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(searchKey(resume.getUuid()), resume);
        }
    }

}
