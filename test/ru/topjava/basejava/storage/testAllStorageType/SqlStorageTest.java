package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.web.SqlStorageInstance;

class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(SqlStorageInstance.getInstance().getSqlStorageInstance());
    }
}