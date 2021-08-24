package duke.task;

import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {
    
    /** The tasks contained within the task list **/
    ArrayList<Task> tasks;

    /**
     * Empty constructor for a task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs the task list from an arraylist of tasks.
     * @param tasks The arraylist used to construct the tasklist.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if tasklist is empty.
     * @return true if empty; else false.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Checks for the size of the tasklist.
     * @return An integer indicating the size of the tasklist.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the task at a given index.
     * @param idx The index that you wish to get the task from.
     * @return The task at the given index.
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Returns the list of tasks.
     * @return the arraylist of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes tasks from the list.
     * @param idx The index that you wish to remove the task from.
     * @return The task that is removed.
     */
    public Task remove(int idx) {
        return this.tasks.remove(idx);
    }

    /**
     * Add tasks to list.
     * @param task The task that you wish to add to the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }
    
}
