package ru.topjava.basejava;

import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.storage.SortedArrayStorage;
import ru.topjava.basejava.storage.Storage;

class TestSortedArray {

    private static final Storage sortArray = new SortedArrayStorage();

    public static void main(String[] args) {

        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid6");
        Resume r3 = new Resume("uuid23");
        Resume r4 = new Resume("uuid16");
        Resume r5 = new Resume("uuid6");

        sortArray.save(r3);
        sortArray.save(r1);
        sortArray.save(r2);
        printAll();

        System.out.println("test save duplicate. Except: Not saved, uuid already exist. _Actual:");
        sortArray.save(r5);

        System.out.println("Get dummy: " + sortArray.get("dummy"));

        sortArray.save(r4);
        printAll();

        sortArray.save(r2);

        sortArray.delete("uuid16");
        printAll();
        sortArray.save(r4);
        sortArray.delete("uuid23");
        printAll();
        sortArray.save(r3);
        sortArray.delete("uuid6");
        printAll();

        sortArray.clear();
        printAll();
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : sortArray.getAll()) {
            System.out.println(r);
        }
    }
}
