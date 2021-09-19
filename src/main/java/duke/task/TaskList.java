package duke.task;

import java.util.ArrayList;

import duke.error.DukeException;

/**
 * Contains the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     *
     * @param tasks The list of tasks.
     */
    public TaskList (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Updates the status of the task in index to done.
     *
     * @param index Index of the task to be updated.
     */
    public void updateStatus(int index) {
        tasks.get(index).updateStatus();
    }

    /**
     * Deletes the task in index from the list.
     *
     * @param index Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds a todo type task to the list.
     *
     * @param description Description of the todo task.
     */
    public void addTodoTask(String description) {
        tasks.add(new TodoTask(description));
    }

    /**
     * Adds a deadline type task to the list.
     *
     * @param description Description of the deadline task.
     * @throws DukeException If the format of the command is incorrect.
     */
    public void addDeadlineTask(String description) throws DukeException {
        int timeIdxStart = description.indexOf("/");

        if (timeIdxStart == -1) {
            throw new DukeException("OOPS!! format for deadline command is incorrect.\nthe format should be:\n"
                    + "\t[description] /by [date]");
        }

        String task = description.substring(0, timeIdxStart);
        String time = description.substring(timeIdxStart + 4);
        tasks.add(new DeadlineTask(task, time));
    }

    /**
     * Adds an event type task to the list.
     *
     * @param description Description of the event task.
     * @throws DukeException If the format of the command is incorrect.
     */
    public void addEventTask(String description) throws DukeException {
        int timeIdxStart = description.indexOf("/");

        if (timeIdxStart == -1) {
            throw new DukeException("OOPS!! format for event command is incorrect.\nthe format should be:\n"
                    + "\t[description] /at [time]");
        }

        String task = description.substring(0, timeIdxStart);
        String time = description.substring(timeIdxStart + 4);

        tasks.add(new EventTask(task, time));
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return tasks;
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task in the specified index.
     *
     * @param index Index of task.
     * @return Task in index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
