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
            if (indexStorage(resume.getUuid()) == -1) {
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
        if (indexStorage(resume.getUuid()) >= 0) {
            storage[indexStorage(resume.getUuid())] = resume;
        } else {
            System.out.println("Такое резюме отсутвует");
        }
    }

    public Resume get(String uuid) {
        if (indexStorage(uuid) >= 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (indexStorage(uuid) >= 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    size--;
                    storage[i] = storage[size];
                    storage[size] = null;
                    break;
                }
            }
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

    int indexStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}
