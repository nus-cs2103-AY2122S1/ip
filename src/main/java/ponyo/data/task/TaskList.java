package ponyo.data.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the list of tasks
 */
public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskId the task to be removed from the list.
     */
    public void remove(int taskId) {
        assert taskId >= 0;
        tasks.remove(taskId);
    }

    /**
     * Get a task from the task list
     *
     * @param taskId the task to be retrieved
     * @return the requested task
     */
    public Task retrieveTask(int taskId) {
        assert taskId >= 0;
        return tasks.get(taskId);
    }

    /**
     * @param task the task to be compared with
     * @return true if task exists in taskList, false otherwise
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Gets the size of the task list
     *
     * @return An integer size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the list is empty
     *
     * @return true if list contains no elements
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
