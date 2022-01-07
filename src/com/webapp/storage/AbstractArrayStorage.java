package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void save(Resume r) {
        if (size != STORAGE_LIMIT) {
            int indexResume = findIndex(r.getUuid());
            if (indexResume < 0) {
                addResume(r, indexResume);
            } else {
                System.out.printf("%nResume uuid=%s exists!%n", r.getUuid());
            }
        } else {
            System.out.println("The record limit has been exceeded!");
        }
    }

    @Override
    public Resume get(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume < 0) {
            System.out.printf("%nResume uuid=%s not exists!%n", uuid);
            return null;
        }
        return storage[indexResume];
    }

    @Override
    public void update(Resume resume) {
        int indexResume = findIndex(resume.getUuid());
        if (indexResume < 0) {
            System.out.printf("%nResume uuid=%s not exists!%n", resume.getUuid());
        } else {
            storage[indexResume] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume == -1) {
            System.out.printf("%nResume uuid=%s not exists!%n", uuid);
        } else {
            fillNullElement(indexResume);
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
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
