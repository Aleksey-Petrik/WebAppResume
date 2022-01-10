package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String UUID_6 = "uuid6";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_3));
        storage.save(new Resume(UUID_5));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_4));
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void save() {
        Resume expectedResume = new Resume(UUID_6);
        storage.save(expectedResume);
        Resume actualResume = storage.get("uuid6");
        Assert.assertEquals(expectedResume, actualResume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() {
        storage.save(new Resume(UUID_2));
    }

    @Test
    public void get() {
        Resume actualResume = storage.get("uuid3");
        Resume expectedResume = new Resume("uuid3");
        Assert.assertEquals(expectedResume, actualResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume expectedResume = new Resume("uuid1");
        storage.update(expectedResume);
        Resume actualResume = storage.get("uuid1");
        Assert.assertEquals(expectedResume, actualResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNoExist() {
        storage.update(new Resume(UUID_6));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid3");
        Assert.assertEquals(4, storage.size());
        storage.get("uuid3");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid6");
    }

    @Test
    public void clear() {
        Assert.assertEquals(5, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] expectedResumesArray = new Resume[]{
                new Resume(UUID_1), new Resume(UUID_2),
                new Resume(UUID_3), new Resume(UUID_4), new Resume(UUID_5)
        };
        Arrays.sort(expectedResumesArray, Resume::compareTo);
        Resume[] actualResumesArray = storage.getAll();
        Arrays.sort(actualResumesArray, Resume::compareTo);
        Assert.assertArrayEquals(expectedResumesArray, actualResumesArray);
    }

    @Test
    public void size() {
        Assert.assertEquals(storage.size(), 5);
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
        storage.save(new Resume(UUID_6));
    }
}