package com.webapp.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid, String message) {
        super(uuid, message);
    }

    public ExistStorageException(String uuid) {
        this(uuid, String.format("%nResume uuid=%s exists!%n", uuid));
    }
}
