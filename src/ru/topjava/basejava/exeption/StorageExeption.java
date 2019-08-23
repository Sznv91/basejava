package ru.topjava.basejava.exeption;

public class StorageExeption extends RuntimeException {

    private final String uuid;

    public StorageExeption(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
