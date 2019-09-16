package ru.topjava.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.topjava.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapStorageTest extends AbstractStorageTest {
    MapStorageTest(){
        super(new MapStorage());
    }

    @Override
    @Test
    void getAll() {
        assertEquals(6, storage.size());
        storage.delete("UUID_1");
        storage.delete("UUID_2");
        storage.delete("UUID_3");
        storage.delete("UUID_4");
        storage.delete("UUID_99");
        storage.delete("UUID_999");
        assertEquals(0,storage.size());
    }

}