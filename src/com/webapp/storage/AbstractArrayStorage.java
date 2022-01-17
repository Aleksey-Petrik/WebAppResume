package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    public static final int STORAGE_LIMIT = 10_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Integer findSearchKey(String uuid);

    protected abstract void fillNullElement(Integer index);

    protected abstract void addResume(Resume r, Integer index);

    @Override
    protected void saveResume(Resume r, Integer index) {
        if (size != STORAGE_LIMIT) {
            addResume(r, index);
            size++;
        } else {
            throw new StorageException(r.getUuid(), "The record limit has been exceeded!");
        }
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected void updateResume(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected void deleteResume(Integer index) {
        fillNullElement(index);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    protected Resume[] getArrayResumes() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected List<Resume> getListResumes() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

}
