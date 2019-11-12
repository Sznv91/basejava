package ru.topjava.basejava.storage;

import ru.topjava.basejava.exeption.StorageException;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.storage.ObjectStreamStorage.StorageStrategy;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {

    private StorageStrategy strategy;

    private Path directory;

    public PathStorage(String dir, StorageStrategy strategy) {
        Objects.requireNonNull(dir, "directory must not be null");
        Objects.requireNonNull(strategy, "Strategy must not be null");
        directory = Paths.get(dir);
        this.strategy = strategy;

        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath().toString() + " is not directory or not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toString() + "/" + uuid);
    }

    @Override
    protected void doSave(Path filePath, Resume r) {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new StorageException("Couldn't create File " + filePath.toString(), filePath.getFileName().toString(), e);
        }
        doUpdate(filePath, r);
    }

    @Override
    protected Resume doGet(Path filePath) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(filePath)));
        } catch (IOException e) {
            throw new StorageException("Path read error", filePath.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageException("file doest'n delete. I/O Exception", file.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Path path, Resume r) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path filePath) {
        return Files.exists(filePath);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        File[] files = directory.toFile().listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(Paths.get(file.getPath())));
        }
        return list;
    }

    @Override
    public void clear() {
        try {
            Files.walkFileTree(directory, new MyFileVisitor());
        } catch (IOException e) {
            throw new StorageException("directory doest'n delete. I/O Exception", null, e);
        }

    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
        }
    }

    @Override
    public int size() {
        File dir = directory.toFile();
        String[] list = dir.list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }

}