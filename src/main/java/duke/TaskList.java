package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represent storage for tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * Creates a TaskList with an empty ArrayList of Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * Creates a TaskList containing the tasks input.
     *
     * @param tasks ArrayList of Tasks to initialize TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the count of Tasks stored.
     *
     * @return count of Tasks stored.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns Task at index.
     * Index is 0-based.
     *
     * @param idx Index of Task to be returned.
     * @return Task stored at index.
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task Task to be added.
     * @return The added Task.
     */
    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Removes Task from TaskList.
     *
     * @param idx Index of Task to be removed.
     * @return The removed Task.
     */
    public Task remove(int idx) {
        Task tempTask = this.tasks.get(idx);
        this.tasks.remove(idx);
        return tempTask;
    }

    /**
     * Indicates whether another object is equals to this TaskList.
     * Two TaskList are only equal if they are of the same length and if the elements of both TaskList
     * at every index are equal to each other.
     *
     * @param other Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this TaskList.
     */
    public boolean equals(TaskList other) {
        return this.tasks.equals(other.getTasks());
    }

    /**
     * Returns the ArrayList of Tasks contained by this TaskList.
     *
     * @return ArrayList of Tasks contained by this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
