package com.webapp.storage;

import com.webapp.util.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}