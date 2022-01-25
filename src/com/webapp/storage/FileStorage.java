package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import com.webapp.storage.serializable.SerializableStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    protected File directory;
    private final SerializableStream serializableStream;

    public FileStorage(File directory, SerializableStream serializableStream) {
        Objects.requireNonNull(directory, " Directory not be Null!");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " Directory is not be file!");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " Error read/write!");
        }
        this.directory = directory;
        this.serializableStream = serializableStream;
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
            serializableStream.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException(file.getAbsolutePath(), "Error update file resume!", e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return serializableStream.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException(file.getAbsolutePath(), "Error read file resume!", e);
        }
    }

    @Override
    protected List<Resume> getListResumes() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Get files error read storage folder!");
        }
        return new ArrayList<>(Arrays.stream(files)
                .map(this::getResume)
                .toList());
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
            throw new StorageException("Get files error read storage folder!");
        }
        return Arrays.stream(files).
                map(this::getResume).toList().toArray(new Resume[0]);
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
