package ru.topjava.basejava.storage.objectStreamStorage;

import java.io.IOException;

@FunctionalInterface
public interface ElementWriter<K,V> {

    void write(K key, V value) throws IOException;
}
