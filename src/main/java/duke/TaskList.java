package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with user.
 * Responds accordingly to user commands and inputs.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task remove(int idx) {
        Task tempTask = this.tasks.get(idx);
        this.tasks.remove(idx);
        return tempTask;
    }

    public boolean equals(TaskList other) {
        return this.tasks.equals(other.getTasks());
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
