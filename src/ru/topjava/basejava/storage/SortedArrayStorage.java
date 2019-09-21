package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.Comparator;

import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getUuid);

    /* Inner Static Class ***
    private static final Comparator<Resume> COMPARATOR = new comprt();

    private static class comprt implements Comparator<Resume>{

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }*/

    /* Anonymous class ***
    private final static Comparator<Resume> COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };*/

    @Override
    protected void pasteResume(int index, Resume resume) {
        int position = -index - 1;
        if (position != size) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected Object getSearchKey(Object uuid) {
        Resume resume = new Resume((String)uuid, "");
        return binarySearch(storage, 0, size, resume, COMPARATOR);
    }

}
