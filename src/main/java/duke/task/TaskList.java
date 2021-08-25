package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t, Ui ui) {
        ui.addTaskMessage(t);
        tasks.add(t);
        ui.printTaskLength(this);
    }

    public void printAllTasks() {
        for (int i = 0; i < this.numberOfTasks(); i++) {
            System.out.println((i + 1) + "." + this.taskNumber(i));
        }
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public Task taskNumber(int i) {
        return tasks.get(i);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }
}
