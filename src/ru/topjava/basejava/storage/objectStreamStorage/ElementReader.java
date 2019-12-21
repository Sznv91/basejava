package ru.topjava.basejava.storage.objectStreamStorage;

import java.io.IOException;

@FunctionalInterface
public interface ElementReader {
    void read() throws IOException;
}
