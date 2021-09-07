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
    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Marks the task in index itemNum (0-indexed) as completed.
     *
     * @param itemNum The number in the TaskList of the duke.Task to be mark completed (0-indexed).
     */
    public void markDone(int itemNum) {
        Task task = tasks.get(itemNum - 1);
        task.markDone();
    }

    /**
     * Gets the task given its index.
     *
     * @param index The index of the Task in the TaskList (0-indexed).
     * @return Task with the index in the TaskList.
     */
    public Task getTaskByIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets length of the TaskList.
     *
     * @return Length of the TaskList.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Deletes a task given its index.
     *
     * @param itemNum The index of the Task in the TaskList(0-indexed).
     */
    public void deleteTask(int itemNum) {
        this.tasks.remove(itemNum);
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
        for (int i = 0; i < this.tasks.size(); i++) {
            Task current = this.tasks.get(i);
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
        String result = "Here are the tasks in your list:\n";
        Task[] taskArray = this.tasks.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            result += String.format("%s. %s", i + 1, taskArray[i].toString());
        }
        return result;
    }
}
