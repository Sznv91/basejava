package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.FileStorage;
import ru.topjava.basejava.storage.objectStreamStorage.DataSerializeStrategyStorage;

class DataStreamFileStorageTest extends AbstractStorageTest {

    DataStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataSerializeStrategyStorage()));
    }

}