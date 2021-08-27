package duke;

import java.util.ArrayList;
import duke.task.Task;

/**
 * Stores list of Task objects
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to list
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task from list
     *
     * @param task task to be deleted
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns number of tasks in list
     *
     * @return number of tasks in list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns task requested
     *
     * @param i index of task requested
     * @return task requested
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns ArrayList of Task objects
     *
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getList() {
        return tasks;
    }
}
