package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.FileStorage;
import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.objectStreamStorage.ObjectStreamStrategyStorage;

class ObjectStreamFileStorageTest extends AbstractStorageTest {

    ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStrategyStorage()));
    }

}