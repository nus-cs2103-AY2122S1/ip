package banana;

import java.util.ArrayList;

/**
 * The TaskList class stores
 * all the tasks.
 *
 * @author: Ravi Ananya
 **/

public class TaskList {

    protected ArrayList<Task> allTasks;

    /**
     * Constructor for the Tasks class.
     *
     * @param allTasks the list of tasks.
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        allTasks.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param t the task to be removed.
     */
    public void removeTask(Task t) {
        allTasks.remove(t);
    }
    
    /**
     * Retrives a task from the list.
     *
     * @param index the index where the task is located.
     * @return the task.
     */
    public Task getTask(int index) {
        return allTasks.get(index);
    }
    
    /**
     * Gets the size of the list.
     *
     * @return the size of the list.
     */
    public int getSize() {
        return allTasks.size();
    }

}
