package duke.task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Ui;

/**
 * Encapsulates a list of tasks.
 * @author Thomas Hogben
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Creates and empty TaskList.
     *
     * @param ui The Ui for the TaskList to send display commands to.
     */
    public TaskList(Ui ui) {
        this.ui = ui;
        tasks = new ArrayList<>();
    }

    /**
     * Creates a non-empty TaskList.
     *
     * @param ui The Ui for the TaskList to send display commands to.
     * @param tasks An ArrayList of Tasks to initialise the TaskList.
     */
    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Adds a Task to the Task List.
     *
     * @param task The new Task to be added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return ui.addTask(task, this);
    }

    /**
     * Marks a specified task as complete.
     * Parses the string after the "done" command and marks the
     * specified task.  Expected format: [task_number]
     *
     * @param input The argument string after the "done" command.
     * @throws DukeException Thrown for any possible error relating to invalid input.
     */
    public String completeTask(String input) throws DukeException {
        int n = parseTaskNumber(input);
        Task task = tasks.get(n);
        task.setDone();
        return ui.completeTask(task);
    }

    /**
     * Deletes a specified task.
     * Parses the string after the "delete" command and deletes the
     * specified task. Expected format: [task_number]
     *
     * @param input The argument string after the "delete" command.
     * @throws DukeException Thrown for any possible error relating to invalid input.
     */
    public String deleteTask(String input) throws DukeException {
        int n = parseTaskNumber(input);
        Task task = tasks.get(n);
        tasks.remove(n);
        return ui.deleteTask(task, this);
    }

    /**
     * @param i The position of the desired task in the list.
     * @return The task at position i in the list.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Lists all the tasks in the TaskList in the Ui.
     */
    public String listTasks() {
        return ui.listTasks(this);
    }

    /**
     * Finds tasks in the TaskList containing the given string and displays them in the Ui.
     * @param str the string to search for.
     */
    public String find(String str) {
        return ui.find(this, str);
    }

    /**
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Parses the task number contained in the input string.
     *
     * @param input A string of an integer.
     * @return The task number in the string.
     * @throws DukeException If the input String is blank, not an integer,
     *                       or not a valid task number.
     */
    private int parseTaskNumber(String input) throws DukeException {
        try {
            if (input.length() < 1) {
                throw DukeException.UNSPECIFIED_TASK;
            } else {
                int i = Integer.parseInt(input/*.substring(1)*/);
                if (i > tasks.size()) {
                    throw DukeException.NOT_ENOUGH_TASKS;
                } else if (i <= 0) {
                    throw DukeException.INVALID_TASK_NUMBER;
                } else {
                    return i - 1;
                }
            }
        } catch (NumberFormatException e) {
            throw DukeException.DEFAULT;
        }
    }
}
