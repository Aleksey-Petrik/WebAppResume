package com.webapp.storage;

import com.webapp.storage.serializable.DataStreamSerializer;

public class DataPathStreamStorageTest extends AbstractStorageTest {

    public DataPathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}