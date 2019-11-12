package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StorageStrategy {
    void doWrite(Resume r, OutputStream outputStream) throws IOException;
    Resume doRead (InputStream inputStream) throws IOException;
}
