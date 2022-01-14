package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Resume r, Integer index) {
        storage[size] = r;
    }

    @Override
    protected void fillNullElement(Integer index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
