package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void saveResume(Resume r, String key) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateResume(Resume r, String key) {
        storage.put(key, r);
    }

    @Override
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    protected List<Resume> getListResumes() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void deleteResume(String key) {
        storage.remove(key);
    }

    @Override
    protected Resume[] getArrayResumes() {
        return storage.values().toArray(new Resume[0]);
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
    protected boolean isExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected String findSearchKey(String uuid) {
        return uuid;
    }
}
