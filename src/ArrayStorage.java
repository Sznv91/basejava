/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        while (size > 0) {
            storage[size - 1] = null;
            size--;
        }
    }

    void save(Resume r) {
        if (r != null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int deleteIndex = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                deleteIndex = i;
            }
        }
        if (deleteIndex != -1) {
            for (int i = deleteIndex; i < size; i++) {
                Resume leftIndex = storage[i];
                storage[i] = storage[i + 1];
                storage[i + 1] = leftIndex;
            }
            size--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] filteredResume = new Resume[size];

        for (int i = 0; i < size; i++) {
            filteredResume[i] = storage[i];
        }
        return filteredResume;
    }

    int size() {
        return size;
    }
}
