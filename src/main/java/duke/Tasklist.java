package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Tasklist {
    private final List<Task> taskList = new ArrayList<>();
    public Tasklist() {

    }

    /**
     * Returns the current number of Tasks in the list.
     *
     * @return Number of Tasks in the list.
     */
    public int size() {
        return taskList.size();
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
        return taskList.get(i);
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
        return taskList.remove(i);
    }

    /**
     * Adds a Task to the current task list.
     * @param t Task passed by the user.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Returns the Tasklist in a file-writable String format.
     *
     * @return Concatenated string of all Tasks in the Tasklist.
     */
    public String toWritable() {
        return taskList.stream().map(task -> task.toString() + "\n").collect(Collectors.joining());
    }

    /**
     * Returns an enumerated Tasklist.
     *
     * @return A ListIterator object to be displayed.
     */
    public ListIterator<Task> toIterable() {
        return taskList.listIterator();
    }
}
