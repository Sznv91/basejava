package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.SqlStorage;

import static ru.topjava.basejava.Config.getInstance;

class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(new SqlStorage(getInstance().getUrlDB(),getInstance().getUserDB(),getInstance().getPasswordDB()));
    }
}