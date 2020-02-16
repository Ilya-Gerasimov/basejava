package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void save(Resume resume) {
        Object searchIndex = getNotExistedSearchIndex(resume.getUuid());
        doSave(resume, searchIndex);
    }

    @Override
    public Resume get(String uuid) {
        Object searchIndex = getExistedSearchIndex(uuid);
        return doGet(searchIndex);
    }

    @Override
    public void update(Resume resume) {
        Object searchIndex = getExistedSearchIndex(resume.getUuid());
        doUpdate(resume, searchIndex);
    }

    @Override
    public void delete(String uuid) {
        Object searchIndex = getExistedSearchIndex(uuid);
        doDelete(searchIndex);
    }

    private Object getNotExistedSearchIndex(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private Object getExistedSearchIndex(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isExist(Object index);

    protected abstract void doSave(Resume resume, Object searchIndex);

    protected abstract Resume doGet(Object searchIndex);

    protected abstract void doUpdate(Resume resume, Object searchIndex);

    protected abstract void doDelete(Object searchIndex);
}

