package duke;

import java.util.ArrayList;

/**
 * Represents the collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class Constructor to create a task list based on the ArrayList of Task.
     *
     * @param tasks tasks to be added to the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Class Constructor for an empty TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task based on the index.
     *
     * @param index index of where to delete.
     * @return the deleted task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the tasklist.
     * @return
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the given index.
     *
     * @param index index of where to retrieve the task.
     * @return the task retrieved.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
