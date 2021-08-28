package main.java.bot;

import main.java.task.Task;

import java.util.ArrayList;

/**
 * A class that encapsulates the list of tasks that the user currently has on Duke.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor for the TaskList class.
     *
     * Takes no parameters.
     */
    TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of Task objects currently stored in the TaskList.
     *
     * Takes no parameters.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Sets the current ArrayList within the TaskList as the one given.
     *
     * @param list The list to be set as the new ArrayList within the TaskList object.
     */
    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns a Task object currently stored in the given index.
     *
     * @param index The index of the Task object to be returned.
     * @return A Task object at the given index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Sets the Task at the index given as the given Task.
     *
     * @param index The index of the Task to be set.
     * @param task The new Task object to replace the current Task object at the given index.
     */
    public void setTask(int index, Task task) {
        this.list.set(index, task);
    }

    /**
     * Adds the given Task to the end of the TaskList.
     *
     * @param task The new Task object to be added to TaskList.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Removes the Task object at the given index.
     *
     * @param index The index of the Task object to be removed.
     */
    public void removeTask(int index) {
        this.list.remove(index);
    }

    /**
     * Returns an integer corresponding to the size of the TaskList, i.e. the number of tasks in the TaskList.
     *
     * Takes no parameters.
     *
     * @return The size of the TaskList.
     */
    public int getSize() { return this.list.size(); }
}
