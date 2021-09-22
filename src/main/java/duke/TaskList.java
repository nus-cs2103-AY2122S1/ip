package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Class used to manage all current tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage = new Storage();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to initialize TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to user's current list of tasks.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
        storage.save(tasks);
    }

    /**
     * Returns a String representation of a completed task.
     *
     * @param i The task no in the list to be marked as completed.
     * @return String representation of the completed task.
     */
    public String markDone(int i) {
        assert i <= tasks.size();
        Task task = getTask(i);
        task.markDone();
        storage.save(tasks);
        return task.toString();
    }

    /**
     * Returns a task at specified position in the list after marking it as done.
     *
     * @param i Position of task in the list.
     * @return Task at position i in the list.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns String representation of deleted task after deleting the task.
     *
     * @param i Position of task in the list to be deleted.
     * @return String representation of the deleted task.
     */
    public String removeTask(int i) {
        assert i > 0;
        Task task = getTask(i);
        tasks.remove(i);
        storage.save(tasks);
        return task.toString();
    }
}
