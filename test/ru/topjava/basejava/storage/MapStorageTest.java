package ru.topjava.basejava.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapStorageTest extends AbstractCollectionStorageTest {
    MapStorageTest(){
        super(new MapStorage());
    }

    @Override
    @Test
    void getAll() {
        assertEquals(6, storage.size());
        assertNotNull(storage.get("UUID_1"));
        storage.delete("UUID_1");
        assertNotNull(storage.get("UUID_2"));
        storage.delete("UUID_2");
        assertNotNull(storage.get("UUID_3"));
        storage.delete("UUID_3");
        assertNotNull(storage.get("UUID_4"));
        storage.delete("UUID_4");
        assertNotNull(storage.get("UUID_99"));
        storage.delete("UUID_99");
        assertNotNull(storage.get("UUID_999"));
        storage.delete("UUID_999");
        assertEquals(0,storage.size());
    }

}