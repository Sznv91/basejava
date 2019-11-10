package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;

import java.io.*;

public class ObjectStreamFileStorage extends AbstractFileStorage {

    public ObjectStreamFileStorage(String directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, OutputStream fileOutputStream) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(file)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
