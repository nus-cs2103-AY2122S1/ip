import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int i) {
        Task task = tasks.get(i);
        task.markAsDone();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
