package com.webapp.storage;

import com.webapp.storage.serializable.XmlStreamSerializer;

public class XmlPathStreamStorageTest extends AbstractStorageTest {

    public XmlPathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}