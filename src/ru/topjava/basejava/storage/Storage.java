package ru.topjava.basejava.storage;

import ru.topjava.basejava.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void update(Resume resume);

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    Resume get(Resume resume);

    void delete(String uuid);

    void delete(Resume resume);

    List<Resume> getAllSorted();

    int size();

}