package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static com.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        try {
            int length = STORAGE_LIMIT - storage.size();
            for (int i = 0; i < length; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Произошло переполнение хранилища!!!");
        }
        storage.save(RESUME_6);
    }
}