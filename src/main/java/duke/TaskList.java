package duke;

import java.util.ArrayList;

/**
 * Object containing the list of tasks currently on hand.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor of a Tasklist object given an ArrayList of tasks.
     * 
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * A constructor of a TaskList with a new ArrayList of tasks. 
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the existing list of tasks.
     * 
     * @param t
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Returns the size of the existing list of tasks.
     * 
     * @return
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a specific task in the list of tasks based on the given index. 
     * 
     * @param i
     * @return
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Removes a specific task in the list of tasks based on the given index.
     * 
     * @param i
     */
    public void remove(int i) {
        this.taskList.remove(i);
    }

}
