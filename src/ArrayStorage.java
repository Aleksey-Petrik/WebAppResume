import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int SIZE_STORAGE = 10_000;
    private final Resume[] storage = new Resume[SIZE_STORAGE];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size != SIZE_STORAGE) {
            if (findIndex(r.uuid) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.printf("%nResume uuid=%s exists!%n", r.uuid);
            }
        } else {
            System.out.println("The record limit has been exceeded!");
        }
    }

    public Resume get(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume != -1) {
            return storage[indexResume];
        }
        System.out.printf("Resume uuid=%s not exists!%n", uuid);
        return null;
    }

    public void delete(String uuid) {
        int indexResume = findIndex(uuid);
        if (indexResume != -1) {
            System.arraycopy(storage, indexResume + 1, storage, indexResume, SIZE_STORAGE - size);
            size--;
        } else {
            System.out.printf("Resume uuid=%s not exists!%n", uuid);
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

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
