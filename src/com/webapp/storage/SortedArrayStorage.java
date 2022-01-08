package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Resume r, int index) {
        index = Math.abs(index) - 1;
        if (storage[index] != null) {
            System.arraycopy(storage, index, storage, index + 1, storage.length - (index + 1));
        }
        storage[index] = r;
    }

    protected void fillNullElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, STORAGE_LIMIT - size);
    }

    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
