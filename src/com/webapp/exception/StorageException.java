package com.webapp.exception;

public class StorageException extends RuntimeException {
    protected final String uuid;

    public StorageException(String uuid) {
        this.uuid = uuid;
    }
}
