package ru.topjava.basejava.storage.testAllStorageType;

import org.junit.jupiter.api.Test;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.MapResumeStorage;

import static org.junit.jupiter.api.Assertions.*;

class MapResumeStorageTest extends AbstractStorageTest {

    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Override
    @Test
    protected void getElement() {
        assertEquals(R_3, storage.get(R_3));
    }

    @Override
    @Test
    protected void save() {
        storage.save(R_5);
        assertEquals(5, storage.size());
        assertEquals(R_5, storage.get(R_5));
    }

    @Override
    @Test
    protected void deleteNotExist() {
        Resume r6 = new Resume(UUID_3, "NeverUsedBefore");
        assertThrows(NotExistStorageException.class, () -> storage.delete(r6));
    }

    @Override
    @Test
    protected void delete() {
        storage.delete(R_1);
        assertThrows(NotExistStorageException.class, () -> storage.get(R_1));
        assertEquals(3, storage.size());
    }

    @Override
    protected void update() {

    }

    @Override
    protected void updateNotExist() {

    }

    @Override
    @Test
    protected void getNotExist() {
        Resume neverUsedBefore = new Resume(UUID_3, "NeverUsedBefore");
        assertThrows(NotExistStorageException.class, () -> storage.get(neverUsedBefore));
    }
}