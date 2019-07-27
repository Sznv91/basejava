
/**
 * Array based storage for Resumes
 */
class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (getIndex(resume.getUuid()) < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Not saved, uuid already exist");
            }
        } else {
            System.out.println(" not enough free cells in storage");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Not delete, UUID " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}