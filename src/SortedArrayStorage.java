import static java.util.Arrays.binarySearch;
import static java.lang.Math.abs;
import static java.util.Arrays.copyOfRange;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Not updated, UUID " + resume.getUuid() + " not found");
        }
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
        rebuildStorage(index, "save");
        storage[index] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            rebuildStorage(index, "delete");
            size--;
        }

    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return binarySearch(storage, 0, size, searchKey);
    }

    private void rebuildStorage(int index, String action) {
        Resume[] fixMassive = new Resume[STORAGE_LIMIT];
        Resume[] leftPart;
        Resume[] rightPart;
        leftPart = copyOfRange(storage, 0, index);
        rightPart = copyOfRange(storage, index, size);
        if (action.equals("save")) {
            System.arraycopy(rightPart, 0, fixMassive, index + 1, rightPart.length);
        } else {
            System.arraycopy(rightPart, 0, fixMassive, index - 1, rightPart.length);
        }
        System.arraycopy(leftPart, 0, fixMassive, 0, leftPart.length);

        storage = fixMassive;
    }
}
