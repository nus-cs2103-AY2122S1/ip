package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int taskNo) {
        return this.tasks.get(taskNo - 1);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public void remove(int taskNo) {
        this.tasks.remove(taskNo - 1);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
