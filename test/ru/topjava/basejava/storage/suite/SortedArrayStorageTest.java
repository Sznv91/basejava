package ru.topjava.basejava.storage.suite;

import ru.topjava.basejava.storage.AbstractArrayStorageTest;
import ru.topjava.basejava.storage.SortedArrayStorage;

class SortedArrayStorageTest extends AbstractArrayStorageTest {

    SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}