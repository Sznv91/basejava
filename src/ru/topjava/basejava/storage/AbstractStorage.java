package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage <SearchKey> implements Storage {

    protected abstract SearchKey getSearchKey(String uuid);

    protected abstract void doSave(SearchKey searchKey, Resume resume);

    protected abstract Resume doGet(SearchKey searchKey);

    protected abstract void doDelete(SearchKey searchKey);

    protected abstract void doUpdate(SearchKey searchKey, Resume resume);

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract List<Resume> doGetAllSorted();

    @Override
    public void save(Resume resume) {
        SearchKey searchKey = getNotExistKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        SearchKey searchKey = getExistKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        SearchKey searchKey = getExistKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public void update(Resume resume) {
        SearchKey searchKey = getExistKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    private SearchKey getExistKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SearchKey getNotExistKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = doGetAllSorted();
        //https://www.baeldung.com/java-8-comparator-comparing
        Comparator<Resume> resumeAgeThanNameComparator
                = Comparator.comparing(Resume::getFullName)
                .thenComparing(Resume::getUuid);

        result.sort(resumeAgeThanNameComparator);
        return result;
    }

}
