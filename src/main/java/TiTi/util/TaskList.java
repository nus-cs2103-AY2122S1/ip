package titi.util;

import java.util.ArrayList;

import titi.task.Task;


/**
 * Represents a list to store tasks.
 * Contains the list of tasks of the user in an ArrayList.
 */
public class TaskList {

    protected ArrayList<Task> list;

    /**
     * Initialises a TaskList instance.
     *
     * @param list ArrayList of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Initialises a TaskList instance.
     * Does not require parameter inputs and create a new empty ArrayList list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Retrieves the required task from the list.
     *
     * @param taskNumber the item number of the task
     * @return the Task object wanted
     */
    public Task get(int taskNumber) {
        return list.get(taskNumber);
    }

    /**
     * Appends new task to end of the list.
     *
     * @param task new task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes task from the list by task.
     *
     * @param task the task to be removed
     */
    public void remove(Task task) {
        list.remove(list.indexOf(task));
    }

}
