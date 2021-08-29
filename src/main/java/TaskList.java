import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        Task task = tasks.get(index);
        tasks.remove(task);
        return task;
    }

    public Task remove(Task task) {
        tasks.remove(task);
        return task;
    }
}
