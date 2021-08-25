package duke;

import java.util.ArrayList;

/**
 * Class for duke.TaskList, containing a list of Tasks.
 * @author Liew Jian Hong
 */

public class TaskList {
    /**
     * ArrayList to store Tasks.
     */
    private ArrayList<Task> al;

    /**
     * Constructor for a duke.TaskList.
     */
    public TaskList() {
        this.al = new ArrayList<Task>();
    }

    /**
     * Adds a task to the duke.TaskList.
     * @param t duke.Task to be added.
     */
    public void addTask(Task t) {
        this.al.add(t);
    }

    /**
     * Marks the task in index itemNum (1-indexed) as completed.
     * @param itemNum The number in the Tasklist of the duke.Task to be mark completed (1-indexed).
     */
    public void markDone(int itemNum) {
        Task task = al.get(itemNum - 1);
        task.markDone();
    }

    /**
     * Gets the task given its index.
     * @param index The index of the duke.Task in the duke.TaskList(0-indexed).
     * @return duke.Task with the index in the duke.TaskList.
     */
    public Task getTaskByIndex(int index) {
        return this.al.get(index);
    }

    /**
     * Gets length of the duke.TaskList.
     * @return Length of the duke.TaskList.
     */
    public int getLength() {
        return this.al.size();
    }

    /**
     * Deletes a task given its index.
     * @param itemNum The index of the duke.Task in the duke.TaskList(0-indexed).
     */
    public void deleteTask(int itemNum) {
        this.al.remove(itemNum);
    }

    public String find(String searchTerm) {
        String result = "";
        int k = 1;
        for (int i = 0; i < this.al.size(); i++) {
            Task current = this.al.get(i);
            if (current.toString().contains(searchTerm)) {
                result += Integer.toString(k) + ". " + current.toString();
                k++;
            }
        }
        return result;
    }

    /**
     * Returns a string representation of the duke.TaskList.
     * @return All the items in the duke.TaskList.
     */
    @Override
    public String toString() {
        String result = "-----------------------------------------\n" + "Here are the tasks in your list:\n";
        Task[] taskArray = this.al.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            result += String.format("%s. %s", i+1, taskArray[i].toString());
        }
        return result + "-----------------------------------------\n";
    }
}
