package com.webapp.storage;

import com.webapp.storage.serializable.XmlStreamSerializer;

public class XmlStreamStorageTest extends AbstractStorageTest {

    public XmlStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}