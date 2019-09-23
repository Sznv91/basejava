package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

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
    public void delete(String uuid) {
        Object searchKey = getExistKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    private Object getExistKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = doGetAllSorted();
        //http://qaru.site/questions/68187/how-to-sort-by-two-fields-in-java
        Collections.sort(result, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                if (o1.getFullName().compareTo(o2.getFullName()) != 0) {
                    return o1.getFullName().compareTo(o2.getFullName());
                } else {
                    return o1.getUuid().compareTo(o2.getUuid());
                }
            }
        });
        return result;
    }

}
