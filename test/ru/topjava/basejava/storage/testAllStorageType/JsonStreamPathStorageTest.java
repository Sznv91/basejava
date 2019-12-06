package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.PathStorage;
import ru.topjava.basejava.storage.objectStreamStorage.JsonSerializeStrategyStorage;

class JsonStreamPathStorageTest extends AbstractStorageTest {

    JsonStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JsonSerializeStrategyStorage()));
    }

}