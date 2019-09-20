package ru.topjava.basejava.storage.suite;

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
    public void getElement(){
        assertEquals(R_3, storage.get(R_3));
    }

    @Override
    public void save(){
        storage.save(R_5);
        assertEquals(5, storage.size());
        assertEquals(R_5, storage.get(R_5));
    }

    @Override
    @Test
    public void delete() {
        storage.delete(R_1);
        assertThrows(NotExistStorageException.class, () -> storage.get(R_1));
        assertEquals(3, storage.size());
    }

    @Override
    public void update() {
        Resume r6 = new Resume(UUID_3, "NeverUsedBefore");
        storage.update(r6);
        assertSame(r6, storage.get(r6));
        assertEquals(4, storage.size());
    }
}