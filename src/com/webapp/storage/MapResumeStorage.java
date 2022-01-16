package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void saveResume(Resume r, Resume key) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateResume(Resume r, Resume key) {
        storage.put(key.getUuid(), r);
    }

    @Override
    protected Resume getResume(Resume key) {
        return key;
    }

    @Override
    protected void deleteResume(Resume key) {
        storage.remove(key.getUuid(), key);
    }

    @Override
    protected Resume[] getArrayResumes() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected List<Resume> getListResumes() {
        return new ArrayList<>(storage.values());
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
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected Resume findSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
