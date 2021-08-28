package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * TaskList class handles the manipulation of the list
 * during active usage of the Duii Bot.
 */
public class TaskList {
    private ArrayList<Task> list;
    private int listLength;

    /**
     * Public constructor to initialise TaskList with data
     * from the previous session for the Duii Bot.
     *
     * @param tasks Creates a task list if there were remaining tasks
     *              from the previous session.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
        this.listLength = tasks.size();
    }

    /**
     * Public constructor to initialise a new TaskList for
     * the Duii Bot.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.listLength = 0;
    }

    /**
     * Inserts the input task into the end of the ArrayList,
     * and updates the total number of tasks.
     *
     * @param task The task to be added to the list.
     * @throws DukeException If an error has occurred in adding the
     *                       task into the ArrayList.
     */
    public void add(Task task) throws DukeException {
        this.list.add(task);
        this.listLength++;
    }


    /**
     * Removes the task with the corresponding id from the ArrayList,
     * and updates the total number of tasks.
     *
     * @param id The id of the task as per display with the list() method.
     * @throws DukeException This error is thrown if the id specified is invalid.
     * @returns The task object which was removed from the list.
     */
    public Task delete(int id) throws DukeException {
        if (id - 1 > this.listLength) {
            throw new DukeException("The id you entered was invalid!");
        } else {
            Task removedTask = this.list.remove(id - 1);
            this.listLength--;
            return removedTask;
        }
    }

    /**
     * Marks a specific task with the corresponding id as completed.
     *
     * @param id The id of the task as per display with the list() method.
     * @throws DukeException This error is thrown if the id specified is invalid.
     * @returns The task object which was marked as done.
     */
    public Task markDone(int id) throws DukeException {
        if (id - 1 > this.listLength) {
            throw new DukeException("The id you entered was invalid!");
        } else {
            Task doneTask = this.list.get(id - 1);
            doneTask.complete();
            return doneTask;
        }
    }

    /**
     * Returns the ArrayList consisting of all the tasks in the active session.
     *
     * @return ArrayList consisting of all the tasks in the active session.
     */
    public ArrayList<Task> getAllTasks() {
        return this.list;
    }
}
