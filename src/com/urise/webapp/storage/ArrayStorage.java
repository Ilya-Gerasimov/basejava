package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < 10000) {
            if (getIndex(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Такое резюме уже существует");
            }
        } else {
            System.out.println("База данных резюме заполнена!");
        }
    }

    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            storage[getIndex(resume.getUuid())] = resume;
        } else {
            System.out.println("Такое резюме отсутвует");
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) >= 0) {
            return storage[getIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (getIndex(uuid) >= 0) {
            size--;
            storage[getIndex(uuid)] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Такое резюме отсутвует");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}
