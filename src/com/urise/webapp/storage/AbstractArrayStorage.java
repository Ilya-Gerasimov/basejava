package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void doSave(Resume resume, int index) {
        if (size < storage.length) {
            insertResume(resume, index);
            size++;
        } else {
            throw new StorageException("БД резюме заполнена", resume.getUuid());
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
    public Resume doGet(int index) {
        return storage[index];
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    public void doDelete(int index) {
        size--;
        deleteResume(index);
        storage[size] = null;
    }

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);
}

