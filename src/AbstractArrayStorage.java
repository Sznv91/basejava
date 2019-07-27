import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
abstract class AbstractArrayStorage implements Storage {

    static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT]; //be final
    int size = 0;

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        System.out.println("Resume \"" + uuid + "\" doesn't found in massive");
        return null;
    }

    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    abstract public int getIndex(String uuid);
}