package sariel.util.tasks;

import java.util.ArrayList;
import java.util.function.Predicate;






/**
 * A class representing a list of tasks.
 *
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Method for checking if a given task is in an ArrayList.
     *
     * @param t The task to check.
     * @param arr The ArrayList it may or may not be in.
     * @return True if the task is in the arraylist and false otherwise.
     */
    public static boolean isAdded(Task t, ArrayList<? extends Task> arr) {
        assert arr != null : "ArrayList added is null";
        assert t != null : "Task to check is null";
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that returns a TaskList of all the tasks that satisfies the
     * boolean condition.
     *
     * @param predicate The test condition.
     * @return
     */
    public TaskList filter(Predicate<? super Task> predicate) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            if (predicate.test(this.get(i))) {
                result.add(this.get(i));
            }
        }
        return result;
    }



}
