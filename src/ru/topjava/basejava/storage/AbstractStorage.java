package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    public abstract int size();

    public abstract Resume get(String uuid);

    public abstract void update(Resume resume);

    public abstract void delete(String uuid);

}
