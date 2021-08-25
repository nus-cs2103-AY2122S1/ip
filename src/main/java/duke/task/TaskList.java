package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class encapsulates a list of tasks that the user has stored in Duke.
 */
public class TaskList {
    /** The list of task objects on Duke. */
    private List<Task> taskList;

    /** The number of tasks that the user currently has on their task list. */
    private int numberOfTasks;

    /**
     * Constructs a task list object with no tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    /**
     * Constructs a task list object with a specified list of tasks.
     *
     * @param taskList The list of tasks in the task list.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
        this.numberOfTasks = taskList.size();
    }

    /**
     * Returns the current number of tasks in the task list object.
     *
     * @return The current number of tasks in the task list.
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     * Returns a task object at a specified index in the task list.
     *
     * @param taskIndex The index of the task to be retrieved.
     * @return The task object located at the index specified.
     */
    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
        this.numberOfTasks++;
    }

    /**
     * Deletes a task at a specified index in the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return The task object representing the task deleted.
     */
    public Task delete(int taskIndex) {
        Task deletedTask = this.taskList.remove(taskIndex);
        this.numberOfTasks--;
        return deletedTask;
    }

    /**
     * Completes a task at a specified index in the task list.
     *
     * @param taskIndex The index of the task to be completed.
     * @return The task object representing the task completed.
     */
    public Task markAsDone(int taskIndex) {
        this.taskList.get(taskIndex).markAsDone();
        return getTask(taskIndex);
    }

    /**
     * Returns the format in which the task list is stored in the save file.
     *
     * @return A string representing how the task list is saved.
     */
    public String getSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numberOfTasks; i++) {
            sb.append(this.taskList.get(i).getSaveFormat());
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return A string representing the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numberOfTasks; i++) {
            sb.append(String.format("%d.%s\n", i + 1, this.taskList.get(i).toString()));
        }
        return sb.toString();
    }
}
