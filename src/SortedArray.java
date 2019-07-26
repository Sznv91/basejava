import static java.util.Arrays.binarySearch;
import static java.lang.Math.abs;
import static java.util.Arrays.copyOfRange;

public class SortedArray extends AbstractArrayStorage {

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index <= 0) {
            index = abs(index) - 1;
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exist");
            return;
        }
        makeHole(index);
        storage[index] = resume;
        size++;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " not found");
            return null;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0){
            
        }

    }

    private int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return binarySearch(storage, 0, size, searchKey);
    }

    private void makeHole(int index) {
        Resume[] fixMassive = new Resume[STORAGE_LIMIT];
        Resume[] leftPart = copyOfRange(storage, 0, index);
        Resume[] rightPart = copyOfRange(storage, index, size);
        System.arraycopy(leftPart, 0, fixMassive, 0, leftPart.length);
        System.arraycopy(rightPart, 0, fixMassive, index + 1, rightPart.length);
        storage = fixMassive;
    }
}
