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
        this.al = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.al.add(t);
    }

    /**
     * Marks the task in index itemNum (1-indexed) as completed.
     *
     * @param itemNum The number in the TaskList of the duke.Task to be mark completed (1-indexed).
     */
    public void markDone(int itemNum) {
        Task task = al.get(itemNum - 1);
        task.markDone();
    }

    /**
     * Gets the task given its index.
     *
     * @param index The index of the Task in the TaskList(0-indexed).
     * @return duke.Task with the index in the duke.TaskList.
     */
    public Task getTaskByIndex(int index) {
        return this.al.get(index);
    }

    /**
     * Gets length of the TaskList.
     *
     * @return Length of the TaskList.
     */
    public int getLength() {
        return this.al.size();
    }

    /**
     * Deletes a task given its index.
     *
     * @param itemNum The index of the Task in the TaskList(0-indexed).
     */
    public void deleteTask(int itemNum) {
        this.al.remove(itemNum);
    }

    /**
     * Finds the tasks (represented by a string) given a search term.
     *
     * @param searchTerm User input to find relevant tasks.
     * @return String representation of tasks relevant to the search term.
     */
    public String find(String searchTerm) {
        String result = "";
        int k = 1;
        for (int i = 0; i < this.al.size(); i++) {
            Task current = this.al.get(i);
            if (current.toString().contains(searchTerm)) {
                result += k + ". " + current;
                k++;
            }
        }
        return result;
    }

    /**
     * Returns a string representation of the TaskList.
     *
     * @return All the items in the TaskList.
     */
    @Override
    public String toString() {
        String result = "-----------------------------------------\n" + "Here are the tasks in your list:\n";
        Task[] taskArray = this.al.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            result += String.format("%s. %s", i + 1, taskArray[i].toString());
        }
        return result + "-----------------------------------------\n";
    }
}
