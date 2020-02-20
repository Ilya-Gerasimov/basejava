package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    public void doSave(Resume resume, Integer index) {
        if (size < storage.length) {
            insertResume(resume, (Integer) index);
            size++;
        } else {
            throw new StorageException("БД резюме заполнена", resume.getUuid());
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[(Integer) index];
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        storage[(Integer) index] = resume;
    }

    @Override
    public void doDelete(Integer index) {
        size--;
        deleteResume((Integer) index);
        storage[size] = null;
    }

    @Override
    protected boolean isExist(Integer index) {
        return (Integer) index >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract Integer searchKey(String uuid);

    protected abstract void insertResume(Resume resume, int index);
}

