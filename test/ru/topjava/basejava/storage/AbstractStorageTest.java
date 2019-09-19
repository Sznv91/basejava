package ru.topjava.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final String UUID_5 = "UUID_5";
    private static final Resume R_1 = new Resume(UUID_1, "Anton");
    private static final Resume R_2 = new Resume(UUID_2, "Boris");
    private static final Resume R_3 = new Resume(UUID_3, "Carl");
    private static final Resume R_4 = new Resume(UUID_4, "Daniel");
    private static final Resume R_5 = new Resume(UUID_5, "Eugen");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
        storage.save(R_4);
    }

    @Test
    void save() {
        storage.save(R_5);
        assertEquals(5, storage.size());
        assertEquals(R_5, storage.get(UUID_5));
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(R_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
        assertEquals(3, storage.size());
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_5));
    }

    @Test
    void update() {
        Resume r6 = new Resume(UUID_3, "NeverUsedBefore");
        storage.update(r6);
        assertSame(r6, storage.get(UUID_3));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(R_5));
    }

    @Test
    void getElement() {
        assertEquals(R_3, storage.get(UUID_3));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid-1"));
    }

    @Test
    void getAllSorted() {
        List<Resume> expect = asList(R_1,R_2,R_3,R_4);
        assertEquals(expect, storage.getAllSorted());
        assertEquals(4, storage.size());
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