package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        assert index < tasks.size();
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Deletes a task specified by index */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task does not exit");
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

}
