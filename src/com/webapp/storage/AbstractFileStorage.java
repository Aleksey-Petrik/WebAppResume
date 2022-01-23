package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    protected File directory;

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file);// throws IOException;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, " Directory not be Null!");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " Directory is not be file!");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " Error read/write!");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void saveResume(Resume r, File file) {
        try {
            directory.createNewFile();
        } catch (IOException e) {
            throw new StorageException(r.getUuid(), "Error create file resume", e);
        }
        updateResume(r, file);
    }

    @Override
    protected void updateResume(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException(file.getAbsolutePath(), "Error update file resume!", e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        return doRead(file);
    }

    @Override
    protected List<Resume> getListResumes() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Clear error read storage folder!");
        }
        return Arrays.stream(files)
                .map(this::doRead)
                .toList();
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException(file.getName(), "Error file delete IO");
        }
    }

    @Override
    protected Resume[] getArrayResumes() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Clear error read storage folder!");
        }
        return (Resume[]) Arrays.stream(files).
                map(this::doRead)
                .toArray();
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Clear error read storage folder!");
        }
        Arrays.asList(files)
                .forEach(this::deleteResume);
    }

    @Override
    public int size() {
        String[] files = directory.list();
        if (files == null) {
            throw new StorageException("Size error read storage folder!");
        }
        return files.length;
    }
}
