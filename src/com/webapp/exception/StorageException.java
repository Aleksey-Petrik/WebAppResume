package com.webapp.exception;

public class StorageException extends RuntimeException {
    protected final String uuid;
    protected final String message;

    public StorageException(String message) {
        this(null, message);
    }

    public StorageException(String uuid, String message) {
        super(message);
        this.uuid = uuid;
        this.message = message;
    }

    public StorageException(String uuid, String message, Exception e) {
        super(message, e);
        this.uuid = uuid;
        this.message = message;
    }
}