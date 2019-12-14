package ru.topjava.basejava.storage.objectStreamStorage;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface SimpleFuncInterface<K,V> {
    //void doWork(Consumer<? super T> action);

     void doWork(BiConsumer<? super K, ? super V> action);

    //public void justEmpty();

}
