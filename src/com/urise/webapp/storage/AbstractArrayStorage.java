package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Резюме " + uuid + " отсутсвует");
        return null;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме " + resume.getUuid() + " отсутсвует ");
        }
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                insertResume(resume, index);
                size++;
            } else {
                System.out.println("Резюме " + resume.getUuid() + " уже существует");
            }
        } else {
            System.out.println("База данных резюме заполнена!");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            deleteResume(index);
            storage[size] = null;
        } else {
            System.out.println("Резюме " + uuid + " отсутвует");
        }
    }

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);
}

