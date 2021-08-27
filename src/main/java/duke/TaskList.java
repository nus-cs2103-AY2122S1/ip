package duke;

import java.util.ArrayList;
import duke.task.Task;

/**
 * Stores list of Task objects
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs TaskList object
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds task to list
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes task from list
     *
     * @param task task to be deleted
     */
    public void deleteTask(Task task) {
        list.remove(task);
    }

    /**
     * Returns number of tasks in list
     *
     * @return number of tasks in list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns task requested
     *
     * @param i index of task requested
     * @return task requested
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Returns ArrayList of Task objects
     *
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getList() {
        return list;
    }
}
