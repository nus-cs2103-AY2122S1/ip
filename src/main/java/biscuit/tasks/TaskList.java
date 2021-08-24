package biscuit.tasks;

import biscuit.exceptions.BiscuitException;
import biscuit.tasks.Task;

import java.util.List;

/**
 * biscuit.tasks.TaskList class contains the task list and operations to edit the list
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Constructor for biscuit.tasks.TaskList class
     *
     * @param list biscuit.tasks.Task List
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds task to list
     *
     * @param task biscuit.tasks.Task to add
     * @throws BiscuitException Error when saving
     */
    public void addTask(Task task) throws BiscuitException {
        list.add(task);
    }

    /**
     * Gets size of list
     *
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Gets task at index
     *
     * @param index Index of task to get
     * @return biscuit.tasks.Task
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Removes task at index
     *
     * @param index Index of task to remove
     */
    public void removeTask(int index) {
        list.remove(index);
    }

    /**
     * Checks if list is empty
     *
     * @return Boolean of is task empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
