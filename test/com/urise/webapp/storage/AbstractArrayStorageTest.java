package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final String UUID_5 = "uuid5";
    private static final Resume RESUME_5 = new Resume(UUID_5);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }

    @Test
    public void size() throws Exception {
        assertSize(4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertSize(3);
    }

    @Test
    public void getAll() {
        Resume[] getStorage = storage.getAll();
        assertEquals(getStorage[0], RESUME_1);
        assertEquals(getStorage[1], RESUME_2);
        assertEquals(getStorage[2], RESUME_3);
        assertEquals(getStorage[3], RESUME_4);
    }

    @Test
    public void get() {
        assertEquals(storage.get(UUID_1), RESUME_1);
        assertEquals(storage.get(UUID_2), RESUME_2);
        assertEquals(storage.get(UUID_3), RESUME_3);
        assertEquals(storage.get(UUID_4), RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_3);
        storage.update(resume);
        assertEquals(storage.get(UUID_3), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_5);
    }

    @Test
    public void save() {
        storage.save(RESUME_5);
        assertSize(5);
        assertEquals(storage.get(UUID_5), RESUME_5);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 5; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException test) {
            System.out.println(test.getMessage());
            Assert.fail();
        }
        storage.save(new Resume());
    }

    private void assertSize(int i) {
        assertEquals(i, storage.size());
    }
}