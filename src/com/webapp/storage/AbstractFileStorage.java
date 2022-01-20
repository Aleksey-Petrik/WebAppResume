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
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException(r.getUuid(), "Error create file resume", e);
        }
    }

    @Override
    protected void updateResume(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException(r.getUuid(), "Error create file resume", e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        return doRead(file);
    }

    @Override
    protected List<Resume> getListResumes() {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .map(this::doRead).toList();
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException(file.getName(), "Error file delete IO");
        }
    }

    @Override
    protected Resume[] getArrayResumes() {
        return (Resume[]) Arrays.stream(Objects.requireNonNull(directory.listFiles())).map(this::doRead).toArray();
    }

    @Override
    public void clear() {
        Arrays.asList(Objects.requireNonNull(directory.listFiles())).forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }
}
