package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}