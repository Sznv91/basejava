import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    void update(Resume original, Resume updateResume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(original.getUuid())) {
                storage[i] = updateResume;
                break;
            }
        }
    }

    void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        boolean resumeExist = false;
        if (resume != null && size < storage.length - 1) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(resume)) {
                    resumeExist = true;
                    break;
                }
            }
            if (resumeExist == false) {
                storage[size] = resume;
                size++;
            }
        } else {
            System.out.print("Resume \"" + resume + "\" doesn't save");
            if (resume == null) {
                System.out.println(" because resume can't be \"null\"");
            }
            if (size >= storage.length - 1) {
                System.out.println(" not enough free cells in massive");
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Resume \"" + uuid + "\" doesn't found in massive");
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
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
}
