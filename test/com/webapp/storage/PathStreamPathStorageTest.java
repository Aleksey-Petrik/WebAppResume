package com.webapp.storage;

import com.webapp.storage.serializable.ObjectStreamStorageSerializer;

public class PathStreamPathStorageTest extends AbstractStorageTest {

    public PathStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorageSerializer()));
    }
}