import static java.util.Arrays.binarySearch;
import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index <= 0) {
            index = abs(index) - 1;
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exist");
            return;
        }
        System.arraycopy(storage, index,
                storage, index + 1, size);
        storage[index] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1,
                    storage, index, size);
            size--;
        }
    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return binarySearch(storage, 0, size, searchKey);
    }
}
