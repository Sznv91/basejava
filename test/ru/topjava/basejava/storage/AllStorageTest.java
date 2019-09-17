package ru.topjava.basejava.storage;

import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({ArrayStorage.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUUIDStorageTest.class,
        MapHashStorageTest.class})
public class AllStorageTest {
}
