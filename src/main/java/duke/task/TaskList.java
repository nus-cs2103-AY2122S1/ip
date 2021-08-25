package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks. It supports operations to add and remove tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a task list with tasks given inside list.
     *
     * @param list Contains a list of tasks to be initialized in TaskList.
     */
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

    /**
     * Returns the the number of tasks in the list.
     *
     * @return Size of list.
     */
    public int numberOfTasks() {
        return list.size();
    }

    /**
     * Returns the given task according to its position in list.
     *
     * @param i The position of the task to be returned.
     * @return The task given by position i.
     */
    public Task taskNumber(int i) {
        return list.get(i);
    }

    /**
     * Removes a task from the task list.
     *
     * @param i The task to be removed.
     */
    public void removeTask(int i) {
        list.remove(i);
    }
}
