package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final HashMap<Resume, Resume> storage = new HashMap<>();


    @Override
    protected Object getSearchKey(Object resume) {
        try {
            return (Resume) resume;
        } catch (ClassCastException e) {
            return (String) (resume);
        }
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistKey(resume);
        doSave(searchKey, resume);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        storage.put((Resume) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        Resume existResume = null;
        for (Map.Entry<Resume, Resume> entry : storage.entrySet()) {
            if (entry.getKey().getUuid().equals((String)searchKey)) {
                existResume = new Resume(entry.getKey().getUuid(), entry.getKey().getFullName());
            }
        }
        storage.remove(existResume);
        storage.put(resume, resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        try {
            ((Resume)searchKey).getUuid();
            return storage.containsKey(searchKey);
        } catch (ClassCastException e) {
            for (Map.Entry<Resume, Resume> entry : storage.entrySet()) {
                if (entry.getKey().getUuid().equals((String) searchKey)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(Resume resume) {
        String searchKey = (String) getExistKey(resume.getUuid());
        if (isExist(searchKey)) {
            doUpdate(searchKey, resume);
        }
    }
}
