package eightbit.util;

import java.util.List;

import eightbit.task.Task;

/**
 * Represents the user's list of tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a list of the user's tasks.
     *
     * @param tasks A list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index Position of task.
     * @return Task at given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a Task in this list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from this list.
     *
     * @param index Position of task.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
}
