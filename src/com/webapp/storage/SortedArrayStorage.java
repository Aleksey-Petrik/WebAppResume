package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Resume r, Integer index) {
        index = Math.abs(index) - 1;
        if (storage[index] != null) {
            System.arraycopy(storage, index, storage, index + 1, storage.length - (index + 1));
        }
        storage[index] = r;
    }

    protected void fillNullElement(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, STORAGE_LIMIT - size);
    }

    protected Integer findSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, searchKey, Comparator.comparing(Resume::getUuid));
    }

}
