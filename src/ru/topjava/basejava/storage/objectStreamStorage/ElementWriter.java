package ru.topjava.basejava.storage.objectStreamStorage;

import java.io.IOException;

@FunctionalInterface
public interface ElementWriter<T> {

    void write(T t) throws IOException;
}
