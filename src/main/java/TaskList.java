import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int i) {
        return tasks.get(i - 1);
    }

    public Task remove(int i) {
        return tasks.remove(i - 1);
    }

    public int size() {
        return tasks.size();
    }
}
