package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrap out a list containing the tasks of the user.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Add new task to the task list.
     * @param task The new task user wants to add to the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Return the current number of elements in the list.
     *
     * @return The current number of elements in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Get the item that user wants.
     *
     * @param i The number of item that user wants to get.
     * @return The task that user wants to get.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Remove the task from the task list.
     *
     * @param i The number of the task which user wants to remove.
     * @return The removed task.
     */
    public Task remove(int i) {
        return this.tasks.remove(i);
    }
}
