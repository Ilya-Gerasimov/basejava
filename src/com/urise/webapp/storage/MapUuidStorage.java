package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void doSave(Resume r, Object uuid) {
        map.put((String) uuid, r);
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public Resume doGet(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    public void doUpdate(Resume r, Object uuid) {
        map.put((String) uuid, r);
    }

    @Override
    public void doDelete(Object uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected Object searchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey((String) uuid);
    }
}
