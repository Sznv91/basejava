package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                int position = (index * (-1)) - 1;
                System.arraycopy(storage, position, storage, position + 1, size);
                storage[position] = resume;
                size++;
            } else {
                System.out.println("Not saved, uuid already exist");
            }
        } else {
            System.out.println(" not enough free cells in storage");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            System.arraycopy(storage, index + 1, storage, index, size);
            size--;
        } else {
            System.out.println("Not delete, UUID " + uuid + " not found");
        }
    }

    @Override
    int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return binarySearch(storage, 0, size, searchKey);
    }
}
