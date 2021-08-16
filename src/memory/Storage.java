package memory;

import java.util.Arrays;

public class Storage {
    private final String[] storage;
    private int index;

    public Storage() {
        storage = new String[100];
        index = 0;
    }

    public boolean push(String value) {
        if (index >= storage.length) {
            return false;
        }
        storage[index] = value;
        index += 1;
        return true;
    }

    public String[] getStorage() {
        return Arrays.copyOfRange(storage, 0, index);
    }
}