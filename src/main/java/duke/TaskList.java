package duke;

import java.util.ArrayList;

import duke.task.Task;



/**
 * Represents a wrapper class for an arraylist of Tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from an existing arraylist of Tasks.
     *
     * @param tasks The given arraylist.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Performs an adding of a task to a TaskList.
     *
     * @param task The given task.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Returns a task at a given index.
     *
     * @param index The given index.
     * @return the task at the given index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the size of the tasklist.
     *
     * @return The size of a tasklist.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Displays all the tasks in a tasklist.
     */
    public String showTask() {
        String temp = "";
        for (int i = 0; i < this.list.size(); i++) {
            temp += String.format("%s. %s\n", i + 1, this.list.get(i).toString());
        }
        return temp;
    }

    /**
     * Performs a deletion of a task at a given index.
     *
     * @param index The given index.
     */
    public void delete(int index) {
        this.list.remove(index - 1);
    }
}

