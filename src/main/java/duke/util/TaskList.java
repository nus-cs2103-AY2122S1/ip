package duke.util;

import java.util.ArrayList;
import java.util.Collection;

import duke.task.Task;

/**
 * Class representing a list of tasks to complete.
 *
 */
public class TaskList {
    /** ArrayList storing the tasks.*/
    private ArrayList<Task> tasks;

    /**
     * Empty constructor of the Tasklist. Used when an empty TaskList is needed.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Overridden constructor of the Tasklist. Used when a TaskList is initialised with values.
     *
     * @param tasks Tasks to initialise tasklist with
     */
    public TaskList(Collection<? extends Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a given task to the tasklist
     *
     * @param task task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns number of tasks in the tasklist
     *
     * @return Number of tasks in the tasklist
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task from the tasklist.
     *
     * @param i The index of task to retrieve
     * @return Task at a given index
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes a task from the tasklist
     *
     * @param i Index of task to remove.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }
}
