package com.webapp.storage;

import com.webapp.storage.serializable.ObjectStreamStorageSerializer;

public class PathStreamStorageTest extends AbstractStorageTest {

    public PathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorageSerializer()));
    }
}