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

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final Resume RESUME_5 = new Resume(UUID_5);
    private static final Resume RESUME_6 = new Resume(UUID_6);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
        storage.save(RESUME_5);
    }

    @Test
    public void save() {
        storage.save(RESUME_6);
        assertSize(6);
        Assert.assertEquals(RESUME_6, storage.get(UUID_6));
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_6);
    }

    @Test
    public void update() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNoExist() {
        storage.update(RESUME_6);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        assertSize(4);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_6);
    }

    @Test
    public void clear() {
        assertSize(5);
        storage.clear();
        assertSize(0);
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
        assertSize(5);
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

    private void assertSize(int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }
}