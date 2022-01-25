package com.webapp.storage.serializable;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorageSerializer implements SerializableStream {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException(null, "Error read resume", e);
        }
    }
}
