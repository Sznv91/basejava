/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        int clearCounter = 0;
        while (this.size > 0) {
            if (storage[clearCounter] != null) {
                storage[clearCounter] = null;
                this.size--;
            }
            clearCounter++;
        }
    }

    void save(Resume r) {
        if (r != null) {
            for (int i = 0; i < storage.length - 1; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    this.size++;
                    break;
                }
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] filteredResume = new Resume[size];
        int filterIterator = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                filteredResume[filterIterator] = storage[i];
                filterIterator++;
            }
        }
        return filteredResume;
    }

    int size() {
        return this.size;
    }
}
