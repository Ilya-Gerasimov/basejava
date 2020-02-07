import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countAttempts = -1;
    private static Resume r;

    void clear() {
        for (int i = 0; i < countAttempts + 1; i++) {
            storage[i] = null;
        }
        countAttempts = -1;
    }

    void save(Resume r) {
        this.r = r;
        countAttempts++;
        storage[countAttempts] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < countAttempts + 1; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < countAttempts + 1; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[countAttempts];
                storage[countAttempts] = null;
                countAttempts--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, (countAttempts + 1));
    }

    int size() {
        return countAttempts + 1;
    }
}
