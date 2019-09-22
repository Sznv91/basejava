package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final Map storage = new HashMap<String,Resume>();

    @Override
    protected Object getSearchKey(Object searchKey) {
        return null;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {

    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
