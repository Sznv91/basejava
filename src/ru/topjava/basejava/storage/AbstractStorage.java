package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(Object searchKey);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> doGetAllSorted();

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public Resume get(Resume resume) {
        Object searchKey = getExistKey(resume);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public void delete(Resume resume) {
        Object searchKey = getExistKey(resume);
        doDelete(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    private Object getExistKey(Object key) {

        Object searchKey = getSearchKey(key);
        if (!isExist(searchKey)) {
            try {
                throw new NotExistStorageException(((Resume) key).getUuid());
            } catch (Exception e) {
                throw new NotExistStorageException((String) key);
            }
        }


        return searchKey;
    }

    protected Object getNotExistKey(Object key) {
        Object searchKey = getSearchKey(key);
        if (isExist(searchKey)) {
            try {
                throw new ExistStorageException(((Resume) key).getUuid());
            } catch (Exception e) {
                throw new ExistStorageException((String) key);
            }

        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = doGetAllSorted();
        result.sort(Comparator.comparing(Resume::getFullName));
        return result;
    }

}
