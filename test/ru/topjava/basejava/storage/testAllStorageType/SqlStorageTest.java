package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.Config;
import ru.topjava.basejava.storage.AbstractStorageTest;

class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(Config.getInstance().getSqlStorageInstance());
    }
}