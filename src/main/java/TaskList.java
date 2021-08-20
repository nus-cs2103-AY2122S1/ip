import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    // TODO consider changing this constructor so that tasks cannot be directly accessed by others
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int i) {
        return tasks.get(i - 1);
    }

    public int size() {
        return tasks.size();
    }
}
