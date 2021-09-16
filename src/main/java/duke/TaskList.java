package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the task collection
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes an instance of TaskList class.
     * Creates an ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks.
     * @return The number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the Task object at a given index of the list of tasks.
     * @param index The list index at which the Task has to be accessed
     * @return The Task object
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new Task
     * @param task The Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a Task from the list at a given index.
     * @param index The index at which the Task is to be removed
     * @return The removed Task object
     */
    public Task remove(int index) {
        Task task = tasks.get(index);
        tasks.remove(task);
        return task;
    }

    /**
     * Removes a Task from the list.
     * @param task The Task object to be removed
     * @return The removed Task object
     */
    public Task remove(Task task) {
        tasks.remove(task);
        return task;
    }
}
