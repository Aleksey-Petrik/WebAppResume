package com.webapp.storage;

import com.webapp.Config;
import com.webapp.ResumeTestData;
import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.ContactType;
import com.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String UUID_5 = "uuid5";
    protected static final String UUID_6 = "uuid6";

    protected static Resume RESUME_1;
    protected static Resume RESUME_2;
    protected static Resume RESUME_3;
    protected static Resume RESUME_4;
    protected static Resume RESUME_5;
    protected static Resume RESUME_6;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();

        RESUME_1 = ResumeTestData.fillResume(UUID_1, "Tobias Blair");
        RESUME_2 = ResumeTestData.fillResume(UUID_2, "Christopher Johnston");
        RESUME_3 = ResumeTestData.fillResume(UUID_3, "Dorthy Daniel");
        RESUME_4 = ResumeTestData.fillResume(UUID_4, "Alexandrina Ray");
        RESUME_5 = ResumeTestData.fillResume(UUID_5, "Elijah Reynolds");
        RESUME_6 = ResumeTestData.fillResume(UUID_6, "Dorthy Daniel");

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
        RESUME_1.setFullName("Alex Petrov");
        RESUME_1.getContacts().put(ContactType.PHONE_NUMBER, "+79886667799");
        RESUME_1.getContacts().put(ContactType.SKYPE, "grigory.kislin70");
        storage.update(RESUME_1);
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
                RESUME_1, RESUME_2,
                RESUME_3, RESUME_4, RESUME_5
        };
        Arrays.sort(expectedResumesArray, Resume::compareTo);
        Resume[] actualResumesArray = storage.getAll();
        Arrays.sort(actualResumesArray, Resume::compareTo);
        Assert.assertArrayEquals(expectedResumesArray, actualResumesArray);
    }

    @Test
    public void getList() {
        List<Resume> expectedList = Arrays.asList(RESUME_1, RESUME_2,
                RESUME_3, RESUME_4, RESUME_5);
        List<Resume> actualList = storage.getAllSorted();
        expectedList.sort(Resume::compareTo);
        assertSize(5);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void size() {
        assertSize(5);
    }

    private void assertSize(int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }

}
