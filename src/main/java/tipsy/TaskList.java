package tipsy;

import java.util.ArrayList;

/**
 * TaskList is the class that contains the list of tasks.
 * It provides operations to add and delete tasks from the list,
 * and mark tasks as complete.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Class constructor specifying an initial list of tasks.
     *
     * @param tasks The list of tasks to initialise with.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the task of the specified index from the list.
     * Uses 1-based indexing.
     *
     * @param taskIndex The new task to be added.
     * @return The task with the specified index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Marks the task of the specified index in the list as done.
     * Uses 1-based indexing.
     *
     * @param taskIndex The index of the task to be marked as done.
     * @return The task that has been marked as done.
     */
    public Task completeTask(int taskIndex) {
        tasks.get(taskIndex - 1).setDone(true);
        return tasks.get(taskIndex - 1);
    }

    /**
     * Deletes a task of the specified index from the list.
     * Uses 1-based indexing.
     *
     * @param taskIndex The index of the task to be deleted from the list.
     * @return The task that has been deleted from the list.
     */
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex - 1);
    }
}
