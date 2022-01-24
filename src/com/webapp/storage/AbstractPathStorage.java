package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    protected Path directory;

    protected abstract void doWrite(Resume r, OutputStream file) throws IOException;

    protected abstract Resume doRead(InputStream file) throws IOException;

    public AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, " Directory not be Null!");
        if (!Files.isWritable(directory) || !Files.isReadable(directory)) {
            throw new IllegalArgumentException(dir + " Error read/write!");
        }
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return Paths.get(directory + "\\" + uuid);
    }

    @Override
    protected void saveResume(Resume r, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException(r.getUuid(), "Error create file resume", e);
        }
        updateResume(r, file);
    }

    @Override
    protected void updateResume(Resume r, Path file) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file.toFile())));
        } catch (IOException e) {
            throw new StorageException(file.toString(), "Error update file resume!", e);
        }
    }

    @Override
    protected Resume getResume(Path file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file.toFile())));
        } catch (IOException e) {
            throw new StorageException(file.toString(), "Error read file resume!", e);
        }
    }

    @Override
    protected List<Resume> getListResumes() {
        try {
            return new ArrayList<>(Files.list(directory).map(this::getResume).toList());
        } catch (IOException e) {
            throw new StorageException("Get files error read storage folder!");
        }
    }

    @Override
    protected void deleteResume(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException(file.toString(), "Error file delete IO");
        }
    }

    @Override
    protected Resume[] getArrayResumes() {
        try {
            return Files.list(directory).map(this::getResume).toList().toArray(new Resume[0]);
        } catch (IOException e) {
            throw new StorageException("Get files error read storage folder!");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Clear error read storage folder!");
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Size error read storage folder!");
        }
    }
}
