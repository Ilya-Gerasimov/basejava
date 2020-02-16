package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void doSave(Resume resume, Object searchIndex) {
        list.add(resume);
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public Resume doGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    public void doDelete(Object index) {
        list.remove(((Integer) index).intValue());
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
