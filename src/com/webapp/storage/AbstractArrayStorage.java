package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void save(Resume r) {
        if (size != STORAGE_LIMIT) {
            int indexResume = findIndex(r.getUuid());
            if (indexResume < 0) {
                addResume(r, indexResume);
                size++;
            } else {
                throw new ExistStorageException(r.getUuid(), String.format("%nResume uuid=%s exists!%n", r.getUuid()));
            }
        } else {
            throw new StorageException(r.getUuid(), "The record limit has been exceeded!");
        }
    }

    @Override
    public Resume get(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume < 0) {
            throw new NotExistStorageException(uuid, String.format("%nResume uuid=%s not exists!%n", uuid));
        }
        return storage[indexResume];
    }

    @Override
    public void update(Resume r) {
        int indexResume = findIndex(r.getUuid());
        if (indexResume < 0) {
            throw new NotExistStorageException(r.getUuid(), String.format("%nResume uuid=%s not exists!%n", r.getUuid()));
        } else {
            storage[indexResume] = r;
        }
    }

    @Override
    public void delete(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume < 0) {
            throw new NotExistStorageException(uuid, String.format("%nResume uuid=%s not exists!%n", uuid));
        } else {
            fillNullElement(indexResume);
            size--;
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void fillNullElement(int index);

    protected abstract void addResume(Resume r, int index);
}
