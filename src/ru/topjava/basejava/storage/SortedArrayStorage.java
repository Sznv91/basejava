package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(int index, Resume resume) {
        int position = (index * (-1)) - 1;
        System.arraycopy(storage, position, storage, position + 1, size);
        storage[position] = resume;
        size++;
    }

    @Override
    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size);
        size--;
    }

    @Override
    int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return binarySearch(storage, 0, size, searchKey);
    }
}
