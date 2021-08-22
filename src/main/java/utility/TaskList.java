package utility;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    protected void deleteTask(int index) {
        this.tasks.remove(index);
    }

    protected void updateTask(int index) {
        this.tasks.get(index).markAsDone();
    }

    protected List<Task> showTasks() {
        return this.tasks;
    }
}
