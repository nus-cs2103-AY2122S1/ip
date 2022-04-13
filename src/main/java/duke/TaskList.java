package duke;

import java.util.ArrayList;

/**
 * This class manages all requests made to the taskList.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks Stores the taskList memory as an ArrayList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task to the taskList.
     *
     * @param t The taks being added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Delete a task from the taskList.
     *
     * @param index Index of the task to remove from the taskList.
     * @return The task being deleted.
     */
    public Task delete(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    /**
     * Marks a task as completed from the taskList.
     *
     * @param index Index of the task to select from the taskList.
     * @return The task being marked as completed.
     */
    public Task done(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        return t;
    }

    /**
     * Lists the currently existing tasks.
     *
     * @return The String form that will be passed to Ui to be shown to the user.
     */
    public String list() {
        int count = 1;
        StringBuilder result = new StringBuilder("     Here are the tasks in your list:\n");
        for (Task task : tasks) {
            result.append(String.format("     %d.%s\n", count, task));
            count++;
        }
        return result.toString();
    }

    /**
     * Returns the size of the current taskList.
     *
     * @return The size of the current taskList.
     */
    public int getSize() {
        return tasks.size();
    }

    public String find(String keyword) {
        int count = 1;
        StringBuilder result =
                new StringBuilder("     Here are the matching tasks in your list:\n");
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                result.append(String.format("     %d.%s\n", count, task));
                count++;
            }
        }
        return result.toString();
    }
}
