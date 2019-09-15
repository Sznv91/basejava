package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void pasteResume(int index, Resume resume) {
        int position = index * (-1) - 1;
        if (position != size) {
            System.arraycopy(storage, position, storage, position + 1, (size - position));
        }
        storage[position] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index, (size - index) - 1);
    }

    @Override
    protected Object searchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return binarySearch(storage, 0, size, searchKey);
    }

}
