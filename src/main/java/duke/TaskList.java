package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 *
 * @author botr99
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList that contains no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList that contain the tasks
     * in the specified ArrayList of tasks.
     *
     * @param tasks The tasks to be contained in
     *              the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return The added task.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks the nth task in the task list
     * to be done, whereby n represents the task number;
     * and updates the storage.
     *
     * @param taskNumber The number n to access the nth task in the task list.
     * @return The task that was marked as done.
     */
    public Task markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes the nth task in the task list,
     * whereby n represents the task number.
     *
     * @param taskNumber The number n to access the nth task in the task list.
     * @return The task that was deleted.
     */
    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Filters tasks that contains the query into another TaskList.
     *
     * @param query The keyword to search for in the task list.
     * @return A new task list containing tasks that satisfy
     *         the query.
     */
    public TaskList getFilteredTasks(String query) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }

    /**
     * Gets the current size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task located at the index.
     *
     * @param index The zero-based index of the task list.
     * @return The task accessed from the index of the task list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a string whereby the task list is represented
     * as a numbered list.
     *
     * @return The string representation of a task list.
     */
    @Override
    public String toString() {
        StringBuilder tasksString = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            int taskNumber = i + 1;
            tasksString
                    .append(taskNumber)
                    .append(".")
                    .append(getTask(i))
                    .append(i == (getSize() - 1) ? "" : "\n");
        }

        return tasksString.toString();
    }

}
