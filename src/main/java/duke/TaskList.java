package duke;

import duke.task.Task;
import duke.exception.DukeException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int listLength;


    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
        this.listLength = tasks.size();
    }

    public TaskList() {
        this.list = new ArrayList<>();
        this.listLength = 0;
    }

    /**
     * This method adds the input task into the list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) throws DukeException {
        this.list.add(task);
        this.listLength++;
    }

    /**
     * This method deletes specific task from the list.
     *
     * @param id The id of the task as per display with the list() method.
     * @throws DukeException This error is thrown if the id specified is invalid.
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
     * This method marks a specific task as completed.
     *
     * @param id The id of the task as per display with the list() method.
     * @throws DukeException This error is thrown if the id specified is invalid.
     */
    public Task markDone(int id) throws DukeException{
        if (id - 1 > this.listLength) {
            throw new DukeException("The id you entered was invalid!");
        } else {
            Task doneTask = this.list.get(id - 1);
            doneTask.complete();
            return doneTask;
        }
    }

    public ArrayList<Task> getAllTasks() {
        return this.list;
    }
}
