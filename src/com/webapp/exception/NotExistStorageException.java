package com.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid, String message) {
        super(uuid, message);
    }

    public NotExistStorageException(String uuid) {
        this(uuid, String.format("%nResume uuid=%s not exists!%n", uuid));
    }
}
