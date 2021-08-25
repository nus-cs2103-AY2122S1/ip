package duke.task;

import duke.DukeException;
import duke.Ui;

import java.util.ArrayList;

/**
 * This class manage all the tasks of Duke.
 */
public class TaskList {
    /** List of tasks. */
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
     * @param tasks the given task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task the given task.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task specified by the given index from the list and returns the task.
     *
     * @param index the given index.
     * @return the removed task.
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
     * @return the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> result = new ArrayList<>(this.tasks);
        return result;
    }

    /**
     * Returns the size of the tasks list.
     *
     * @return the size of the tasks list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks.
     */
    public void showTasks() {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(Ui.INDENTATION + "Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println(Ui.INDENTATION + "No tasks");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.println(Ui.INDENTATION + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    /**
     * Marks the task specified by the given index as done.
     *
     * @param index the given index.
     * @throws DukeException if the index is out of range.
     */
    public void markTaskAsDone(int index) throws DukeException{
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
