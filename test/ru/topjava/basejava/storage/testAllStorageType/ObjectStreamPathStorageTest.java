package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.PathStorage;
import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.ObjectStreamStorage.ObjectStreamStrategyStorage;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamStrategyStorage()));
    }

}