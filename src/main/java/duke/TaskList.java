package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // add
    public void addTask(Task task) {
        tasks.add(task);
    }

    // delete
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    // getSize()
    public int getSize() {
        return tasks.size();
    }

    // getTask()
    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
}
