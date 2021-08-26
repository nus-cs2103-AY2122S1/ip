import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

}
