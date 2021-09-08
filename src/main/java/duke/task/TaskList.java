package duke.task;

import duke.DukeException;
import duke.Ui;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
        this.tasks = new ArrayList<>();
    }

    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Adds a Task to the Task List.
     *
     * @param task The new Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.addTask(task, this);
    }

    /**
     * Marks a specified task as complete.
     * Parses the string after the "done" command and marks the
     * specified task.  Expected format: " " + task_number
     *
     * @param input The argument string after the "done" command.
     * @throws DukeException Thrown for any possible error relating to invalid input.
     */
    public void completeTask(String input) throws DukeException {
        int n = parseTaskNumber(input);
        Task task = tasks.get(n);
        task.setDone();
        ui.completeTask(task);
    }

    /**
     * Deletes a specified task.
     * Parses the string after the "delete" command and deletes the
     * specified task. Expected format: " " + task_number
     *
     * @param input The argument string after the "delete" command.
     * @throws DukeException Thrown for any possible error relating to invalid input.
     */
    public void deleteTask(String input) throws DukeException {
        int n = parseTaskNumber(input);
        Task task = tasks.get(n);
        tasks.remove(n);
        ui.deleteTask(task, this);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void listTasks() {
        ui.listTasks(this);
    }

    public void find(String str) {
        ui.find(this, str);
    }

    public int size() {
        return tasks.size();
    }

    private int parseTaskNumber(String input) throws DukeException {
        try {
            if (input.length() < 1) {
                throw new DukeException(DukeException.UNSPECIFIED_TASK);
            } else {
                int i = Integer.parseInt(input/*.substring(1)*/);
                if (i > tasks.size()) {
                    throw new DukeException(DukeException.NOT_ENOUGH_TASKS);
                } else if (i <= 0) {
                    throw new DukeException(DukeException.INVALID_TASK_NUMBER);
                } else {
                    return i - 1;
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.DEFAULT);
        }
    }
}
