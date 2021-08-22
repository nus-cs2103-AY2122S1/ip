package util.tasks;

import java.util.ArrayList;


/**
 * A class representing a list of tasks.
 *
 */
public class TaskList extends ArrayList<Task>{

    /**
     * Method for checking if a given task is in an ArrayList.
     *
     * @param t The task to check.
     * @param arr The ArrayList it may or may not be in.
     * @return True if the task is in the arraylist and false otherwise.
     */
    public static boolean isAdded(Task t, ArrayList<? extends Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(t)) return true;
        }
        return false;
    }


}
