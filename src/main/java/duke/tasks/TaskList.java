package duke.tasks;

import java.util.ArrayList;

/**
 * Encapsulates the information of a TaskList object that contains an Arraylist.
 * Includes methods for manipulating the TaskList object.
 */
public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds the specified task to the Arraylist.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.items.add(task);
    }

    /**
     * Removes the specified task from the Arraylist.
     *
     * @param id The index of the task to be removed.
     */
    public void deleteTask(int id) {
        this.items.remove(id);
    }

    /**
     * Marks the specified task as completed.
     *
     * @param id The index of the task to be marked as completed.
     */
    public void taskCompleted(int id) {
        this.items.get(id).taskCompleted();
    }

    /**
     * Returns the number of tasks in the Arraylist.
     *
     * @return
     */
    public int getTaskCount() {
        return this.items.size();
    }

    /**
     * Returns the specified task.
     *
     * @param id The index of the task to be obtained.
     * @return The task with the specified index in the Arraylist.
     */
    public Task getTask(int id) {
        return this.items.get(id);
    }
}
