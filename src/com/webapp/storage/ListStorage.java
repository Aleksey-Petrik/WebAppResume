package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected void saveResume(Resume r, Integer index) {
        storage.add(r);
    }

    @Override
    protected void updateResume(Resume r, Integer index) {
        storage.add(index, r);
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage.get(index);
    }

    @Override
    protected List<Resume> getListResumes() {
        return storage;
    }

    @Override
    protected void deleteResume(Integer index) {
        storage.remove((index).intValue());
    }

    @Override
    protected Resume[] getArrayResumes() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        int counter = 0;
        for (Resume resume : storage) {
            if (uuid.equals(resume.getUuid())) {
                return counter;
            }
            counter++;
        }
        return -1;
    }
}
