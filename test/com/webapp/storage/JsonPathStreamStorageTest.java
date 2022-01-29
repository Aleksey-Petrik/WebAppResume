package com.webapp.storage;

import com.webapp.storage.serializable.JsonStreamSerializer;

public class JsonPathStreamStorageTest extends AbstractStorageTest {

    public JsonPathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}