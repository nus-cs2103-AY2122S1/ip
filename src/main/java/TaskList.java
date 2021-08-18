import java.util.ArrayList;

/**
 * Class for TaskList, containing a list of Tasks.
 * @author Liew Jian Hong
 */

public class TaskList {
    /**
     * ArrayList to store Tasks.
     */
    private ArrayList<Task> al;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.al = new ArrayList<Task>();
    }

    /**
     * Adds a task to the TaskList.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.al.add(t);
    }

    /**
     * Mark the task in index itemNum (1-indexed) as completed.
     * @param itemNum The number in the Tasklist of the Task to be mark completed (1-indexed).
     */
    public void markDone(int itemNum) {
        Task task = al.get(itemNum - 1);
        task.markDone();
    }

    /**
     * Get the task given its index.
     * @param index The index of the Task in the TaskList(0-indexed).
     * @return Task with the index in the TaskList.
     */
    public Task getTaskByIndex(int index) {
        return this.al.get(index);
    }

    /**
     * Get length of the TaskList.
     * @return Length of the TaskList.
     */
    public int getLength() {
        return this.al.size();
    }

    /**
     * Deletes a task given its index.
     * @param itemNum The index of the Task in the TaskList(0-indexed).
     */
    public void deleteTask(int itemNum) {
        this.al.remove(itemNum);
    }

    /**
     * Return a string representation of the TaskList.
     * @return All the items in the TaskList.
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
