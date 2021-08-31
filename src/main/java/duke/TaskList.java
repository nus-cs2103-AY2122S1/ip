package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    Task getTask(int index) {
        assert index < tasks.size();
        return tasks.get(index);
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }

    void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task does not exit");
        }
    }

    void addTask(Task t) {
        tasks.add(t);
    }

}
