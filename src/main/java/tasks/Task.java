package tasks;

import java.util.List;
import java.util.ArrayList;

/**
 * This class encapsulates a user-added task
 *
 * @author kevin9foong
 */
public class Task {
    private String description;
    private boolean isDone = false;
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public Task() {}

    public Task(String description) {
        this.description = description;
    }

    /**
     * Get task associated with index number in taskList.
     * @param index index of task to get
     * @return task associated with index number in taskList
     */
    public static Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a new task to the taskList.
     * @param task task to be added
     */
    public static Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Retrieves a new list containing all tasks in the taskList.
     * @return new list containing copy of all tasks from the taskList
     */
    public static List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }

    /**
     * Gets the number of tasks in the taskList.
     * @return int representing number of tasks in the taskList
     */
    public static int getNumOfTasks() {
        return taskList.size();
    }

    /**
     * Marks task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Set description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
