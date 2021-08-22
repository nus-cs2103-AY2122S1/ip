package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.items.add(task);
    }

    public void deleteTask(int id) {
        this.items.remove(id);
    }

    public void markTaskAsComplete(int id) {
        this.items.get(id).markAsComplete();
    }

    public int getTaskCount() {
        return this.items.size();
    }

    public Task getTask(int id) {
        return this.items.get(id);
    }
}
