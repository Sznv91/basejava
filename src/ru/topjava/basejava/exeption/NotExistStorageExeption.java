package ru.topjava.basejava.exeption;

public class NotExistStorageExeption extends StorageExeption {
    public NotExistStorageExeption(String uuid) {
        super(uuid);
    }
}
