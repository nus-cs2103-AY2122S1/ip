package duke.data;
import duke.commands.Task;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public void iterList() {
        System.out.println("Here are the tasks in your list:");
        if (this.lst.size() == 0) {
            System.out.println("---- No Tasks currently ----");
        }
        int i = 1;
        for (Task s : this.lst) {
            System.out.println(i + "." + s);
            i++;
        }
    }

    public void add(Task text) {
        this.lst.add(text);
    }

    public void complete(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = this.lst.get(index);
        task.completeTask();
    }

    public void delete(int index) throws DukeException {
        if (index < 0 || index > this.lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = this.lst.get(index);
        this.lst.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.lst;
    }

    public int count() {
        return this.lst.size();
    }

    public Task get(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = this.lst.get(index);
        return task;
    }
}
