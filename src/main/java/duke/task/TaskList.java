package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Adds a given Task to list.
     * @param task Task to add to list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns a specific task in the list at a given index.
     * @param itemNumber starts from 1.
     * @return Task
     */
    public Task get(int itemNumber) throws IndexOutOfBoundsException {
        int index = itemNumber - 1;
        return list.get(index);
    }

    /**
     * Removes a Task from the TaskList.
     * @param itemNumber registered number of task in list, starts from 1.
     */
    public void delete(int itemNumber) throws IndexOutOfBoundsException {
        int index = itemNumber - 1;
        list.remove(index);
    }

    /**
     * Sets a Task from the TaskList as done.
     * @param itemNumber index of task in list, starts from 1.
     * @return true if successful, false otherwise.
     */
    public void setDone(int itemNumber) throws IndexOutOfBoundsException {
        list.get(itemNumber - 1).markAsDone();
    }
    /**
     * Returns the number of tasks in list.
     * @return size of list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns a String representation of the TaskList.
     * @return Full list of tasks.
     */
    @Override
    public String toString() {

        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += ((i + 1) + ". " + list.get(i));
            if (i != list.size() - 1) {
                output += "\n";
            }
        }

        return output;

    }
}
