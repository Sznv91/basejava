package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.FileStorage;
import ru.topjava.basejava.storage.objectStreamStorage.JsonSerializeStrategy;

class JsonStreamFileStorageTest extends AbstractStorageTest {

    JsonStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new JsonSerializeStrategy()));
    }

}