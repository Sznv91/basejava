package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.FileStorage;
import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.ObjectStreamStorage.ObjectStreamStrategyStorage;

class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStrategyStorage()));
    }

}