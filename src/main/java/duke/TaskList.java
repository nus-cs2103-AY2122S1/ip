package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * This class manages all current tasks
 */
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

    /**
     *
     * @param i The task no in the list to be marked as completed
     * @return String representation of the completed task
     */
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

    /**
     *
     * @param i The task no in the list to be deleted
     * @return String representation of the deleted task
     */
    public String removeTask(int i) {
        Task task = getTask(i);
        tasks.remove(i);
        storage.save(tasks);
        return task.toString();
    }
}
