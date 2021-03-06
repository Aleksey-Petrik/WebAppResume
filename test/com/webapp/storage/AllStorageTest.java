package com.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        FileStreamStorageTest.class,
        PathStreamStorageTest.class,
        XmlPathStreamStorageTest.class,
        JsonPathStreamStorageTest.class,
        DataPathStreamStorageTest.class,
        SqlStorageTest.class
})

public class AllStorageTest {
}
