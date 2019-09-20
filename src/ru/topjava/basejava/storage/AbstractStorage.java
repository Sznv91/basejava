package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(Resume resume);

    public abstract int size();

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> doGetAllSorted();

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistKey(resume);
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume(uuid, "");
        Object searchKey = getExistKey(resume);
        return doGet(searchKey);
    }

    public Resume get(Resume resume) {
        Object searchKey = getExistKey(resume);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid, "");
        Object searchKey = getExistKey(resume);
        doDelete(searchKey);
    }

    @Override
    public void delete(Resume resume) {
        Object searchKey = getExistKey(resume);
        doDelete(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistKey(resume);
        doUpdate(searchKey, resume);
    }

    private Object getExistKey(Resume resume) {
        Object searchKey = getSearchKey(resume);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistKey(Resume resume) {
        Object searchKey = getSearchKey(resume);
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
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
