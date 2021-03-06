package ru.topjava.basejava.storage.testAllStorageType;

import ru.topjava.basejava.storage.AbstractStorageTest;
import ru.topjava.basejava.storage.PathStorage;
import ru.topjava.basejava.storage.objectStreamStorage.XmlSerializeStrategyStorage;

class XmlStreamPathStorageTest extends AbstractStorageTest {

    XmlStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlSerializeStrategyStorage()));
    }

}