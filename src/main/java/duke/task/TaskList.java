package duke.task;

import duke.DukeException;
import duke.Ui;

import java.util.ArrayList;

/**
 * This class manage all the tasks of Duke.
 */
public class TaskList {
    /**
     * List of tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList instance based on the given task list.
     *
     * @param tasks The given task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task The given task.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task specified by the given index from the list and returns the task.
     *
     * @param index The given index.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number: " + (index + 1) + " does not exist.");
        }
        return this.tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> result = new ArrayList<>(this.tasks);
        return result;
    }

    /**
     * Returns the size of the tasks list.
     *
     * @return The size of the tasks list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task specified by the given index as done.
     *
     * @param index The given index.
     * @throws DukeException If the index is out of range.
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number: " + (index + 1) + " does not exist.");
        }
        Task temp = tasks.get(index);
        temp.markAsDone();
        tasks.set(index, temp);
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(Ui.INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(Ui.INDENTATION + temp.toString());
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    /**
     * Returns a list of task that matches the query string.
     *
     * @param query The given query.
     * @return A list of task that matches the query string.
     */
    public ArrayList<Task> findTask(String query) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(query)) {
                result.add(task);
            }
        }
        return result;
    }
}
