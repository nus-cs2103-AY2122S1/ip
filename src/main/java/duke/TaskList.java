package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage = new Storage();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
        storage.save(tasks);
    }

    public String markDone(int i) {
        Task task = getTask(i);
        task.markDone();
        storage.save(tasks);
        return task.toString();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String removeTask(int i) {
        Task task = getTask(i);
        tasks.remove(i);
        storage.save(tasks);
        return task.toString();
    }
}
