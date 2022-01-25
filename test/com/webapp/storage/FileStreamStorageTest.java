package com.webapp.storage;

import com.webapp.storage.serializable.ObjectStreamStorageSerializer;

public class FileStreamStorageTest extends AbstractStorageTest {

    public FileStreamStorageTest() {
        super(new FileStorage((STORAGE_DIR), new ObjectStreamStorageSerializer()));
    }
}