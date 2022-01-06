package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    @Override
    public void save(Resume r) {
        if (size != STORAGE_LIMIT) {
            if (findIndex(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.printf("%nResume uuid=%s exists!%n", r.getUuid());
            }
        } else {
            System.out.println("The record limit has been exceeded!");
        }
    }

    @Override
    public void update(Resume resume) {
        int indexResume = findIndex(resume.getUuid());
        if (indexResume == -1) {
            System.out.printf("%nResume uuid=%s not exists!%n", resume.getUuid());
        } else {
            storage[indexResume] = resume;
        }
    }

    @Override
    public Resume get(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume == -1) {
            System.out.printf("%nResume uuid=%s not exists!%n", uuid);
            return null;
        }
        return storage[indexResume];
    }

    @Override
    public void delete(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume == -1) {
            System.out.printf("%nResume uuid=%s not exists!%n", uuid);
        } else {
            //System.arraycopy(storage, indexResume + 1, storage, indexResume, SIZE_STORAGE - size);
            storage[indexResume] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
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

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
