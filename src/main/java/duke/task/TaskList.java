package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(TaskList tasks, Task t, Ui ui) {
        ui.addTaskMessage(t);
        list.add(t);
        ui.printTaskLength(tasks);
    }

    public int numberOfTasks() {
        return list.size();
    }

    public Task taskNumber(int i) {
        return list.get(i);
    }

    public void removeTask(int i) {
        list.remove(i);
    }
}
