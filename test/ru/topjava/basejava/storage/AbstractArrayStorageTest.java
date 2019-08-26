package ru.topjava.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {

    private final Storage storage;

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String uuid1 = "uuid1";
    private static final Resume r1 = new Resume(uuid1);
    private static final String uuid2 = "uuid2";
    private static final Resume r2 = new Resume(uuid2);
    private static final String uuid3 = "uuid3";
    private static final Resume r3 = new Resume(uuid3);
    private static final String uuid4 = "uuid4";
    private static final Resume r4 = new Resume(uuid4);

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        storage.save(r4);
    }

    @Test
    void save() {
        storage.save(new Resume("uuid5"));
        assertEquals(5, storage.size());
        assertEquals(storage.get("uuid5"), new Resume("uuid5"));
    }

    @Test
    void saveDuplicate() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(uuid1)));
    }

    @Test
    void saveOverflow() {
        while (storage.size() < 10_000) {
            storage.save(new Resume());
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    void delete() {
        storage.delete("uuid1");
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid1"));
        assertEquals(3, storage.size());
    }

    @Test
    void update() {
        Resume r5 = new Resume("uuid3");
        storage.update(r5);
        assertEquals(r5, storage.get("uuid3"));
    }

    @Test
    void getFirstElement() {
        assertEquals(storage.get("uuid1"), new Resume(uuid1));
    }

    @Test
    void getLastElement() {
        assertEquals(storage.get(uuid4), new Resume(uuid4));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid-1"));
    }

    @Test
    void getAll() {
        Resume[] expect = new Resume[4];
        expect[0] = r1;
        expect[1] = r2;
        expect[2] = r3;
        expect[3] = r4;

        assertArrayEquals(expect, storage.getAll());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void size() {
        assertEquals(4, storage.size());
    }
}