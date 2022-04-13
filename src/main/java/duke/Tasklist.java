package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * This class contains methods for manipulating items found in a Duke bot's tasklist.
 */
public class Tasklist {
    private final List<Task> tasks = new ArrayList<>();
    public Tasklist() {

    }

    /**
     * Creates a new Tasklist object instantiated with Task objects stored.
     *
     * @param tasks Task objects to be stored into the new object.
     */
    public Tasklist(List<Task> tasks) {
        for (Task t: tasks) {
            this.tasks.add(t);
        }
    }
    /**
     * Returns the current number of Tasks in the list.
     *
     * @return Number of Tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a Task from the one-based index task list.
     * getTask(2) will return the second Task in the task list.
     *
     * @param idx The position of the Task to be retrieved.
     * @return The Task object requested by the user.
     * @throws IndexOutOfBoundsException Throws an exception if index given does not exist in the current task list.
     */
    public Task getTask(int idx) throws IndexOutOfBoundsException {
        int i = idx - 1;
        return tasks.get(i);
    }

    /**
     * Removes a Task from the one-based index task list.
     * removeTask(2) will remove the second Task in the task list.
     *
     * @param idx The position of the Task to be removed.
     * @return The Task object removed from the task list by the user.
     * @throws IndexOutOfBoundsException Throws an exception if index given does not exist in the current task list.
     */
    public Task removeTask(int idx) throws IndexOutOfBoundsException {
        int i = idx - 1;
        return tasks.remove(i);
    }

    /**
     * Adds a Task to the current task list.
     * @param t Task passed by the user.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Returns a Tasklist containing all the items matching the given keyword.
     *
     * @param keyword String keyword given by the user.
     * @return A Tasklist containing all valid entries from the main list of items.
     */
    public Tasklist findAllBy(String keyword) {
        Tasklist t = new Tasklist(tasks.stream().filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toList()));
        if (t.size() == 0) {
            return null;
        } else {
            return t;
        }
    }
    /**
     * Returns the Tasklist in a file-writable String format.
     *
     * @return Concatenated string of all Tasks in the Tasklist.
     */
    public String toWritable() {
        return tasks.stream().map(task -> task.toString() + "\n").collect(Collectors.joining());
    }

    /**
     * Returns an enumerated Tasklist.
     *
     * @return A ListIterator object to be displayed.
     */
    public ListIterator<Task> toIterable() {
        return tasks.listIterator();
    }
}
