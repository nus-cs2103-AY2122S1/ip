package duke;

import java.util.ArrayList;

import duke.task.Task;



/**
 * Contains the task list
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Class Constructor
     *
     * @param tasks the task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the size of the list
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    public Task get(int taskNo) {
        return this.tasks.get(taskNo - 1);
    }

    /**
     * Checks if there are any tasks in the list
     *
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Removes a task from the list
     *
     * @param taskNo the specified task number
     */
    public void remove(int taskNo) {
        this.tasks.remove(taskNo - 1);
    }

    /**
     * Adds a task to the list
     *
     * @param task the task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }
}
