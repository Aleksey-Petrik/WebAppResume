package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract boolean isExist(T key);

    protected abstract T findSearchKey(String uuid);

    protected abstract void saveResume(Resume r, T key);

    protected abstract void updateResume(Resume r, T key);

    protected abstract Resume getResume(T key);

    protected abstract List<Resume> getListResumes();

    protected abstract void deleteResume(T key);

    protected abstract Resume[] getArrayResumes();

    @Override
    public void save(Resume r) {
        logger.info("Add resume" + r.toString());
        saveResume(r, getNotExistedSearchKey(r.getUuid()));
    }

    @Override
    public void update(Resume r) {
        logger.info("Update resume" + r.toString());
        updateResume(r, getExistedSearchKey(r.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        logger.info("Get resume uuid - " + uuid);
        return getResume(getExistedSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume uuid - " + uuid);
        deleteResume(getExistedSearchKey(uuid));
    }

    @Override
    public Resume[] getAll() {
        logger.info("Get resume array.");
        return getArrayResumes();
    }

    @Override
    public List<Resume> getAllSorted() {
        logger.info("Get resume list.");
        List<Resume> resumes = getListResumes();
        resumes.sort(Resume::compareTo);
        return resumes;
    }

    private T getExistedSearchKey(String uuid) {
        T key = findSearchKey(uuid);
        if (!isExist(key)) {
            logger.warning(String.format("Resume uuid=%s not exists!", uuid));
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private T getNotExistedSearchKey(String uuid) {
        T key = findSearchKey(uuid);
        if (isExist(key)) {
            logger.warning(String.format("Resume uuid=%s exists!", uuid));
            throw new ExistStorageException(uuid);
        }
        return key;
    }

}
