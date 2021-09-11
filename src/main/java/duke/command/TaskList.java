package duke.command;

import duke.exception.DuplicateException;
import duke.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Wraps out a list containing the tasks of the user.
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    /**
     * Adds new task to the task list.
     *
     * @param task The new task user wants to add to the list.
     */
    public void addTask(Task task) throws DuplicateException {
        if (taskList.contains(task)) {
            throw new DuplicateException();
        }
        taskList.add(task);
    }

    /**
     * Returns the current number of elements in the list.
     *
     * @return The current number of elements in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the item that user wants.
     *
     * @param i The number of item that user wants to get.
     * @return The task that user wants to get.
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Removes the task from the task list.
     *
     * @param i The number of the task which user wants to remove.
     * @return The removed task.
     */
    public Task removeTask(int i) {
        return taskList.remove(i);
    }
}
