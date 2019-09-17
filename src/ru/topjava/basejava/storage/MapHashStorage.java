package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.HashMap;

public class MapHashStorage extends MapUUIDStorage {

    public MapHashStorage() {
        super(new HashMap<Integer, Resume>());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid.hashCode();
    }

}
