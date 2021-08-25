package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks. It supports operations to add and remove tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with tasks given inside list.
     *
     * @param tasks Contains a list of tasks to be initialized in TaskList.
     */
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

    /**
     * Returns the the number of tasks in the list.
     *
     * @return Size of list.
     */
    public int numberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the given task according to its position in list.
     *
     * @param i The position of the task to be returned.
     * @return The task given by position i.
     */
    public Task taskNumber(int i) {
        return tasks.get(i);
    }

    /**
     * Removes a task from the task list.
     *
     * @param i The task to be removed.
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }
}
