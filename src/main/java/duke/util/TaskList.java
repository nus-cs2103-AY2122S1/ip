package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(Collection<? extends Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }
}
