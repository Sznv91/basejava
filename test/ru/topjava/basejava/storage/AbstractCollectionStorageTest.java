package ru.topjava.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractCollectionStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final String UUID_5 = "UUID_5";
    private static final String UUID_6 = "UUID_99";
    private static final String UUID_7 = "UUID_999";
    private static final Resume R_1 = new Resume(UUID_1);
    private static final Resume R_2 = new Resume(UUID_2);
    private static final Resume R_3 = new Resume(UUID_3);
    private static final Resume R_4 = new Resume(UUID_4);
    private static final Resume R_5 = new Resume(UUID_5);
    private static final Resume R_6 = new Resume(UUID_6);
    private static final Resume R_7 = new Resume(UUID_7);

    protected AbstractCollectionStorageTest(Storage collection) {
        storage = collection;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
        storage.save(R_4);
        storage.save(R_6);
        storage.save(R_7);
    }

    @Test
    void save() {
        storage.save(R_5);
        assertEquals(7, storage.size());
        assertEquals(R_5, storage.get(UUID_5));
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
        assertEquals(5, storage.size());
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_5));
    }

    @Test
    void update() {
        Resume r6 = new Resume(UUID_3);
        storage.update(r6);
        assertSame(r6, storage.get(UUID_3));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(R_5));
    }

    @Test
    void getElement() {
        assertEquals(R_1, storage.get(UUID_1));
        assertEquals(R_2, storage.get(UUID_2));
        assertEquals(R_3, storage.get(UUID_3));
        assertEquals(R_4, storage.get(UUID_4));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid-1"));
    }

    @Test
    void getAll() {
        Resume[] expect = new Resume[6];
        expect[0] = R_1;
        expect[1] = R_2;
        expect[2] = R_3;
        expect[3] = R_4;
        expect[4] = R_6;
        expect[5] = R_7;

        assertArrayEquals(expect, storage.getAll());
        assertEquals(6, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void size() {
        assertEquals(6, storage.size());
    }

}