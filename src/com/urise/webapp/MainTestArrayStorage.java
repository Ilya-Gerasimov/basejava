package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //private final static Storage ARRAY_STORAGE = new SortedArrayStorage();
    private final static Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");
        Resume r7 = new Resume("uuid7");
        Resume r8 = new Resume("uuid8");

        System.out.println("Сохраняем объекты");
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);

        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r7);
        ARRAY_STORAGE.save(r8);

        printAll();
        ARRAY_STORAGE.save(r5);
        printAll();

        System.out.println("Получаем getUuid");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r2: " + ARRAY_STORAGE.get(r2.getUuid()));
        System.out.println("Get r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        System.out.println("Get r5: " + ARRAY_STORAGE.get(r5.getUuid()));
        System.out.println("Get r6: " + ARRAY_STORAGE.get(r6.getUuid()));
        System.out.println("Get r7: " + ARRAY_STORAGE.get(r7.getUuid()));
        System.out.println("Get r7: " + ARRAY_STORAGE.get(r7.getUuid()));

        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
