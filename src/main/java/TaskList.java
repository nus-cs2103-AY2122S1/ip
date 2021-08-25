import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void add(Task task) {

    }

    public void remove(Task task) {
        tasks.remove(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
