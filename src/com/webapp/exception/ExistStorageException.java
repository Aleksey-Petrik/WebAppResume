package com.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid, String message) {
        super(uuid, message);
    }
}
