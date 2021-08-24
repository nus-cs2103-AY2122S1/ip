package ponyo.data.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int task) {
        tasks.remove(task - 1);
    }

    public Task retrieveTask(int task) {
        return tasks.get(task - 1);
    }

    public int size() {
        return tasks.size();
    }
}
