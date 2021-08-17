import java.util.ArrayList;

public class TaskStorage {
    ArrayList<Task> storage;
    private static TaskStorage instance = null;

    private TaskStorage() {
        this.storage = new ArrayList<>();
    }

    public static TaskStorage getInstance() {
        if (instance == null) {
            return new TaskStorage();
        }
        return instance;
    }

    public ArrayList<Task> getAll() {
        return storage;
    }

    public Task get(int index) {
        return storage.get(index);
    }

    public int getSize() {
        return storage.size();
    }

    public boolean add(Task task) {
        return storage.add(task);
    }

    public Task delete(int index) {
        return storage.remove(index);
    }
}
