import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
class ArrayStorage {

    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    void update(Resume resume) {
        boolean resumeExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i] = resume;
                resumeExist = true;
                break;
            }
        }
        if (!resumeExist) {
            System.out.println("Not updated, UUID " + resume.getUuid() + " not found");
        }
    }

    void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        boolean resumeExist = false;
        if ((resume != null) && (resume.getUuid() != null) && (size < storage.length)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    resumeExist = true;
                    System.out.println("Not saved, uuid already exist");
                    break;
                }
            }
            if (!resumeExist) {
                storage[size] = resume;
                size++;
            }
        } else {
            System.out.print("Resume \"" + resume + "\" doesn't save");
            if (resume == null) {
                System.out.println(" because resume can't be \"null\"");
                return;
            }
            if (size >= storage.length) {
                System.out.println(" not enough free cells in storage");
                return;
            }
            if (resume.getUuid() == null) {
                System.out.println(" because UUID is null");
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
        boolean resumeExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                resumeExist = true;
                break;
            }
        }
        if (!resumeExist) {
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
}
