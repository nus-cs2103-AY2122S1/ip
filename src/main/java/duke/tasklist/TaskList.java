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
    private final ArrayList<Task> tasks;

    // The constructor takes in an array of tasks and saves it as the taskList.
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to taskList.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of elements in the taskList.
     *
     * @return The number of elements in the taskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a specific element from the taskList.
     *
     * @param index The index of the task in the taskList
     * @return The desired element at the index specified
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the StatusString (the string to be saved into the text file) of the last element in the taskList.
     *
     * @return The StatusString (the string to be saved into the text file) of the last element in the taskList
     */
    public String getLastStatusString() {
        return tasks.get(tasks.size() - 1).getStatusString();
    }

    /**
     * Removes a specified element from the taskList.
     *
     * @param index the index of the task to remove
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns an ArrayList of String representing tasks that match the searched word.
     *
     * @param word The word that is searched for in the tasks.
     * @return An ArrayList of String representing tasks that match the searched word.
     */
    public ArrayList<String> search(String word) {
        ArrayList<String> wordList = new ArrayList<>();

        for (Task t : tasks) {
            if (t.toString().toLowerCase().contains(word)) {
                wordList.add((tasks.indexOf(t) + 1) + ". " + t.toString());
            }
        }
        return wordList;
    }
}
