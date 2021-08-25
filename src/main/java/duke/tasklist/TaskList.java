/**
 * This function handles the list of tasks maintained by the system.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */
package duke.tasklist;

import duke.tasks.Task;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    // The constructor takes in an array of tasks and saves it as the taskList.
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * This adds a task to taskList.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the number of elements in the taskList.
     *
     * @return the number of elements in the taskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieves a specific element from the taskList.
     *
     * @param index the index of the task in the taskList
     * @return the desired element at the index specified
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the StatusString (the string to be saved into the text file) of the last element in the taskList.
     *
     * @return the StatusString (the string to be saved into the text file) of the last element in the taskList
     */
    public String getLastStatusString() {
        return taskList.get(taskList.size() - 1).getStatusString();
    }

    /**
     * Retrieves all elements of the taskList.
     *
     * @return all elements of the taskList
     */
    public ArrayList<Task> getAll() {
        return taskList;
    }

    /**
     * Removes a specified element from the taskList.
     *
     * @param index the index of the task to remove
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    public ArrayList<String> search(String word) {
        ArrayList<String> wordList = new ArrayList<String>();
        for (Task t : taskList) {
            if (t.toString().contains(word)) {
                wordList.add(t.toString());
            }
        }
        return wordList;
    }
}
