import java.util.ArrayList;

public class TaskStorage {
    private ArrayList<Task> storage;
    private static TaskStorage instance = null;

    private TaskStorage() {
        this.storage = new ArrayList<>();
    }

    public static TaskStorage getInstance() {
        if (instance == null) {
            instance = new TaskStorage();
            return instance;
        }
        return instance;
    }

    public ArrayList<Task> getAll() {
        return storage;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return storage.get(index);
    }

    public int getSize() {
        return storage.size();
    }

    public boolean add(Task task) {
        return storage.add(task);
    }

    public Task delete(int index) throws IndexOutOfBoundsException {
        return storage.remove(index);
    }
}
