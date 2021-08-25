package duke.data;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** List of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor of the class `TaskList`.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task); // add to task list.
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed.
     */
    public void removeFromList(Task task) {
        this.tasks.remove(task); // remove from task list.
    }

    /**
     * Returns a list of string, which is a copy of `tasks` list.
     *
     * @return A copy of tasks list.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> copy = new ArrayList<>();
        int len = this.getNumOfTasks();
        for (int i = 0; i < len; i++) {
            copy.add(this.tasks.get(i));
        }
        return copy;
    }

    /**
     * Returns a task as a string to be stored in the file.
     *
     * @param index Index of the task.
     * @return String representation of the task.
     */
    public String getFileFormattedTask(int index) {
        return this.tasks.get(index).toFileFormatString();
    }

    /**
     * Returns the number of tasks added.
     *
     * @return Number of tasks.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int indexOf(Task task) {
        return this.tasks.indexOf(task);
    }
}
