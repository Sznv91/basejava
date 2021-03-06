package ru.topjava.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topjava.basejava.Config;
import ru.topjava.basejava.storage.TestData.ResumeTestData;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.NotExistStorageException;
import ru.topjava.basejava.model.Resume;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.getInstance().getSTORAGE_DIR();
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final String UUID_5 = "UUID_5";
    private static final ResumeTestData rtd = new ResumeTestData();
    private static final Resume R_1 = rtd.getR1();
    private static final Resume R_2 = rtd.getR2();
    private static final Resume R_3 = rtd.getR3();//new Resume(UUID_3, "Carl");
    private static final Resume R_4 = rtd.getR4();
    private static final Resume R_5 = new Resume(UUID_5, "Eugen");
    final Storage storage;

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
        storage.delete(UUID_2);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
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
        assertEquals(r6, storage.get(UUID_3));
        assertEquals(4, storage.size());
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(R_5));
    }

    @Test
    void getElement() {
        assertEquals(R_1, storage.get(UUID_1)); //R3
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_5));
    }

    @Test
    void getAllSorted() {
        List<Resume> expect = asList(R_2, R_3, R_1, R_4);
        assertEquals(expect.size(), storage.size());
        assertEquals(expect, storage.getAllSorted());
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