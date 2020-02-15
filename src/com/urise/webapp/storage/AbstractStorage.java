package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void save(Resume resume) {
        int searchIndex = getNotExistedSearchIndex(resume.getUuid());
        doSave(resume, searchIndex);
    }

    @Override
    public Resume get(String uuid) {
        int searchIndex = getExistedSearchIndex(uuid);
        return doGet(searchIndex);
    }

    @Override
    public void update(Resume resume) {
        int searchIndex = getExistedSearchIndex(resume.getUuid());
        doUpdate(resume, searchIndex);
    }

    @Override
    public void delete(String uuid) {
        int searchIndex = getExistedSearchIndex(uuid);
        doDelete(searchIndex);
    }

    private int getNotExistedSearchIndex(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private int getExistedSearchIndex(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume, int searchIndex);

    protected abstract Resume doGet(int searchIndex);

    protected abstract void doUpdate(Resume resume, int searchIndex);

    protected abstract void doDelete(int searchIndex);
}

