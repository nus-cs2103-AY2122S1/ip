package task;

import java.util.ArrayList;

public class TaskList {

    /** ArrayList of Tasks currently */
    private ArrayList<Task> tasks;

    public TaskList() {
    }

    /**
     * Constructor for TaskList.
     * @param tasks ArrayList of Tasks to be loaded.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get a task by its index.
     * @param index Task index to be retrieved.
     * @return Task of index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns number of Tasks currently.
     * @return Number of Tasks currently.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Add a Task.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a Task.
     * @param index Index of Task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }
}
