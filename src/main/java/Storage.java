import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final List<String> storage;

    Storage() {
        this.storage = new ArrayList<String>();
    }

    Storage(List<String> storage) {
        this.storage = storage;
    }

    List<String> getStorage() {
        return this.storage;
    }

    Storage add(String item) {
        List<String> oldStorage = this.storage;
        oldStorage.add(item);

        return new Storage(oldStorage);
    }

    void listItems() {
        for (int i = 0; i < storage.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), storage.get(i));
        }
    }
}
