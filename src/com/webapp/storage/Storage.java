package com.webapp.storage;

import com.webapp.model.Resume;

public interface Storage {
    void clear();

    void save(Resume r);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
