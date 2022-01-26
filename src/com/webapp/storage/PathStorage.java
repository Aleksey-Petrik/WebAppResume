package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import com.webapp.storage.serializable.SerializableStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    protected Path directory;
    private final SerializableStream serializableStream;

    public PathStorage(String dir, SerializableStream serializableStream) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, " Directory not be Null!");
        if (!Files.isWritable(directory) || !Files.isReadable(directory)) {
            throw new IllegalArgumentException(dir + " Error read/write!");
        }
        this.serializableStream = serializableStream;
    }

    @Override
    protected boolean isExist(Path file) {
        /*Files.isRegularFile(file);*/
        return Files.exists(file);
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void saveResume(Resume r, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException(file.getFileName().toString(), "Error create file resume in storage - ".concat(file.toString()), e);
        }
        updateResume(r, file);
    }

    @Override
    protected void updateResume(Resume r, Path file) {
        try {
            serializableStream.doWrite(r, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException(file.getFileName().toString(), "Error update file resume in storage - ".concat(file.toString()), e);
        }
    }

    @Override
    protected Resume getResume(Path file) {
        try {
            return serializableStream.doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException(file.toString(), "Error read file resume!", e);
        }
    }

    @Override
    protected List<Resume> getListResumes() {
        return getFileList()
                .map(this::getResume)
                .collect(Collectors.toList());
    }

    @Override
    protected void deleteResume(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException(file.toString(), "Error file delete IO", e);
        }
    }

    @Override
    protected Resume[] getArrayResumes() {
        //TODO
        return getFileList().map(this::getResume)
                .collect(Collectors.toList())
                .toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        getFileList().forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return (int) getFileList().count();
    }

    private Stream<Path> getFileList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException(null, "Error read storage folder! " + directory.toString(), e);
        }
    }
}
