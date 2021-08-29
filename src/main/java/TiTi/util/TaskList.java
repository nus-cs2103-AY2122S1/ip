package TiTi.util;

import TiTi.task.Task;

import java.util.ArrayList;

/**
 * Contain the list of tasks of the user in an ArrayList.
 */
public class TaskList {

    protected ArrayList<Task> list;

    /**
     * Constructor for TaskList class.
     *
     * @param list ArrayList of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Retrieve the required task from the list.
     *
     * @param taskNumber the item number of the task
     * @return the Task object wanted
     */
    public Task get(int taskNumber) {
        return list.get(taskNumber);
    }

    /**
     * Append new task to end of the list.
     *
     * @param task new task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Return the number of tasks in the list.
     *
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Remove task from the list.
     *
     * @param taskNumber the item number of the task
     */
    public void remove(int taskNumber) {
        list.remove(taskNumber);
    }

}
