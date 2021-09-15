package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a class that stores the array list of tasks and methods that operate on it.
 */
public class TaskList {
    private final List<Task> list = new ArrayList<>();

    /**
     * Adds the provided task to list.
     *
     * @param t The Provided Task object.
     */
    public void addToList(Task t) {
        list.add(t);
    }

    /**
     * Returns a String that describes how many tasks are in the list.
     *
     * @return The String that describes how many tasks are in the list.
     */
    public String numOfTasks() {
        return "Now you have " + list.size() + " task" + (list.size() != 1 ? "s" : "") + " in the list";
    }

    /**
     * Returns a String containing all the tasks in the task list.
     *
     * @return The String containing all the tasks in the task list.
     */
    public String[] printList() {
        String[] res = new String[list.size()];
        for (int counter = 1; counter <= list.size(); counter++) {
            res[counter - 1] = counter + ". " + list.get(counter - 1);
        }
        return res;
    }

    /**
     * Returns a String array to be printed by the UI when a new task is added.
     *
     * @param t The Task that was added.
     * @return The String array to be printed.
     */
    public String[] taskAddedMessage(Task t) {
        return new String[]{
            "Got it, I've added this task:",
            t.toString(),
            numOfTasks()
        };
    }

    /**
     * Returns a String array to be printed by the UI when a task is completed.
     *
     * @param t The Task that was completed.
     * @return The String array to be printed.
     */
    public String[] taskCompletedMessage(Task t) {
        return new String[]{
            "Nice! I've marked this task as done:",
            t.toString(),
            numOfTasks()
        };
    }

    /**
     * Returns a String array to be printed by the UI when a task is deleted.
     *
     * @param t The Task that was deleted.
     * @return The String array to be printed.
     */
    public String[] taskDeletedMessage(Task t) {
        return new String[]{
            "Noted. I've removed this task:",
            t.toString(),
            numOfTasks()
        };
    }

    public int size() {
        return list.size();
    }

    public Task getTask(int index) {
        assert list.size() > index : "List size should be larger than index";
        return list.get(index);
    }

    /**
     * Removes the task at the give index in the task list.
     *
     * @param index The index at which the task should be deleted.
     */
    public void removeTask(int index) {
        assert list.size() > index : "List size should be larger than index";
        list.remove(index);
    }

    /**
     * Returns a String array of tasks which contain the provided search term.
     *
     * @param searchTerm The provided search term.
     * @return The String array of tasks.
     */
    public String[] findInTasks(String searchTerm) {
        List<String> res = new ArrayList<>();
        res.add("Here are the matching tasks in your list:");
        for (Task t : list) {
            if (t.toString().contains(searchTerm)) {
                res.add(t.toString());
            }
        }
        String[] result = new String[res.size()];
        result = res.toArray(result);
        return result;
    }

}
