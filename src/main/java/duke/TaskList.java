package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with user.
 * Responds accordingly to user commands and inputs.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = ui;
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

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
