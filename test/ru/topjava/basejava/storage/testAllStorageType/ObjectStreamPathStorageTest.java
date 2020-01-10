package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.PathStorage;
import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.objectStreamStorage.ObjectStreamStrategyStorage;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStrategyStorage()));
    }

}