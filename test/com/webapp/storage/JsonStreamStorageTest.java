package com.webapp.storage;

import com.webapp.storage.serializable.JsonStreamSerializer;

public class JsonStreamStorageTest extends AbstractStorageTest {

    public JsonStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}