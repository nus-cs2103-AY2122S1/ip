import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> store;

    public TaskList() {
        store = new ArrayList<>();
    }

    public int size() {
        return store.size();
    }

    public Task get(int i) {
        return store.get(i);
    }

    public Task remove(int i) {
        return store.remove(i);
    }

    public void add(Task t) {
        store.add(t);
    }
}
