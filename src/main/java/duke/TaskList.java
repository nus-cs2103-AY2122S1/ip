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

    public void add(Task task) throws DukeException {
        this.list.add(task);
        this.listLength++;
    }

    public Task delete(int id) throws DukeException {
        if (id - 1 > this.listLength) {
            throw new DukeException("The id you entered was invalid!");
        } else {
            Task removedTask = this.list.remove(id - 1);
            this.listLength--;
            return removedTask;
        }
    }

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
