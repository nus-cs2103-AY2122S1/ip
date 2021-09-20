package biscuit.task;

import java.util.List;

/**
 * TaskList class contains the task list and operations to edit the list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs TaskList class.
     *
     * @param tasks Task List.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets size of list.
     *
     * @return Size of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets task at index.
     *
     * @param index Index of task to get.
     * @return Task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes task at index.
     *
     * @param index Index of task to remove.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Checks if list is empty.
     *
     * @return Boolean of is task empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
