package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.FileStorage;
import ru.topjava.basejava.storage.objectStreamStorage.XmlSerializeStrategyStorage;

class XmlStreamFileStorageTest extends AbstractStorageTest {

    XmlStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new XmlSerializeStrategyStorage()));
    }

}