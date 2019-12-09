package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.PathStorage;
import ru.topjava.basejava.storage.objectStreamStorage.DataSerializeStrategyStorage;

class DataStreamPathStorageTest extends AbstractStorageTest {

    DataStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new DataSerializeStrategyStorage()));
    }

}