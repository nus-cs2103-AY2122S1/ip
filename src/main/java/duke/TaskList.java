package duke;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks which is used by Duke
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructor for the TaskList object
     *
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList
     *
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList
     *
     * @param index
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the specified index
     *
     * @param index
     * @return Task at specified index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list of tasks
     *
     * @return Number of tasks in the list of tasks
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

}
