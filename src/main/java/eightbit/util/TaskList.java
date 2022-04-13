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
        assert tasks != null : "Tasks should be initialized";
        return tasks.size();
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index Position of task.
     * @return Task at given index.
     */
    public Task get(int index) {
        assert tasks != null : "Tasks should be initialized";
        return tasks.get(index);
    }

    /**
     * Adds a Task in this list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert tasks != null : "Tasks should be initialized";
        tasks.add(task);
    }

    /**
     * Removes a task from this list.
     *
     * @param index Position of task.
     * @return The removed task.
     */
    public Task remove(int index) {
        assert tasks != null : "Tasks should be initialized";
        return tasks.remove(index);
    }
}
