package jwbot.data;

import java.util.ArrayList;
import java.util.List;

import jwbot.data.task.Task;

/**
 * Class that stores the list of the tasks.
 *
 * @author  Yim Jaewon
 */
public class TaskList {

    private List<Task> items;

    /**
     * The basic constructor of the task list that takes in an arraylist of tasks.
     *
     * @param items the list of the tasks
     */
    public TaskList(List<Task> items) {
        this.items = items;
    }

    /**
     * The alternative constructor made in case an error occurs.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * The method used to access the task arraylist directly.
     *
     * @return the stored private arraylist of the tasks
     */
    public List<Task> getItems() {
        return items;
    }

    /**
     * Removes the item from the list and return it.
     *
     * @param index the index of the item of the list that the user wants to remove
     * @return the removed task
     */
    public Task remove(int index) {
        return items.remove(index);
    }

    /**
     * Get the number of tasks stored currently.
     *
     * @return the size of the stored task arraylist
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Add the task to the list.
     *
     * @param task the task that the user wants to add to the list
     */
    public void addTask(Task task) {
        items.add(task);
    }

    /**
     * Get task from the list.
     *
     * @param index the index of the list of the tasks that the user wants to get the task
     * @return the task from the list of the index
     */
    public Task getTask(int index) {
        return items.get(index);
    }
}
