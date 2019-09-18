package ru.topjava.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
        this.storage = storage;
    }

    private static final int STORAGE_LIMIT = 10000;

    @Test
    void saveOverflow() {
        while (storage.size() < STORAGE_LIMIT) {
            storage.save(new Resume("Test to Overflow"));
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("Exception")));
    }

}
