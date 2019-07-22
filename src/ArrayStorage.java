import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
class ArrayStorage {

    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    void update(Resume resume) {
        int positionResume = positionResume(resume.getUuid());
        if (positionResume > -1) {
            storage[positionResume] = resume;
            System.out.println("Not saved, uuid already exist");
        } else {
            System.out.println("Not updated, UUID " + resume.getUuid() + " not found");
        }
    }

    void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size < storage.length) {
            int positionResume = positionResume(resume.getUuid());
            if (positionResume < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Not saved, uuid already exist");
            }
        } else {
            System.out.println(" not enough free cells in storage");
        }
    }

    Resume get(String uuid) {
        int positionResume = positionResume(uuid);
        if (positionResume > -1) {
            return storage[positionResume];
        }
        System.out.println("Resume \"" + uuid + "\" doesn't found in massive");
        return null;
    }

    void delete(String uuid) {
        int positionResume = positionResume(uuid);
        if (positionResume > -1) {
            storage[positionResume] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Not delete, UUID " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }

    private int positionResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}