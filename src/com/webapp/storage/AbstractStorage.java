package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

public abstract class AbstractStorage<T> implements Storage {

    protected abstract boolean isExist(T key);

    protected abstract T findSearchKey(String uuid);

    protected abstract void saveResume(Resume r, T key);

    protected abstract void updateResume(Resume r, T key);

    protected abstract Resume getResume(T key);

    protected abstract void deleteResume(T key);

    protected abstract Resume[] getArrayResumes();

    @Override
    public void save(Resume r) {
        saveResume(r, getNotExistedSearchKey(r.getUuid()));
    }

    @Override
    public void update(Resume r) {
        updateResume(r, getExistedSearchKey(r.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        return getResume(getExistedSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        deleteResume(getExistedSearchKey(uuid));
    }

    @Override
    public Resume[] getAll() {
        return getArrayResumes();
    }

    private T getExistedSearchKey(String uuid) {
        T key = findSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private T getNotExistedSearchKey(String uuid) {
        T key = findSearchKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

}
