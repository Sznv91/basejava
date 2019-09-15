package ru.topjava.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.topjava.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListStorageTest extends AbstractCollectionStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    void testTime(){
        storage.clear();
        for (int i = 0; i < 10_000; i++){
            Resume resume = new Resume();
            storage.save(resume);
        }
        assertEquals(10_000,storage.size());
    }

}