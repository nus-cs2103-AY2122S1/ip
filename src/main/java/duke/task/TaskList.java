package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TaskList is a list for tasks.
 */
public class TaskList {

    /** Storage container to store tasks. */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new task to the list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task cannot be null.";

        tasks.add(task);
    }

    /**
     * Returns number of Tasks stored in the storage.
     *
     * @return number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the storage is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks a task as done.
     *
     * @param idx index of the task to be marked as done.
     * @throws IllegalArgumentException for invalid index.
     */
    public void markDone(int idx) throws IllegalArgumentException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! Index entered is not valid. "
                    + "Please use 'list' and check for the appropriate index for task(s)."
            );
        }
        tasks.get(idx).markDone();
    }

    /**
     * Gets a task from the storage.
     *
     * @param idx index of the task to get from storage.
     * @return the task.
     * @throws IllegalArgumentException for invalid index.
     */
    public Task get(int idx) throws IllegalArgumentException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new IllegalArgumentException(
                "☹ OOPS!!! Index entered is not valid. "
                + "Please use 'list' and check for the appropriate index for task(s)."
            );
        }
        return tasks.get(idx);
    }

    /**
     * Deletes a task from storage.
     *
     * @param idx index of the task to delete from storage.
     * @throws IllegalArgumentException for invalid index.
     */
    public void delete(int idx) throws IllegalArgumentException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new IllegalArgumentException(
                "☹ OOPS!!! Index entered is not valid. "
                + "Please use 'list' and check for the appropriate index for task(s)."
            );
        }
        tasks.remove(idx);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";

        result += Stream.iterate(0, idx -> idx < tasks.size(), idx -> idx + 1)
                .map(idx -> (idx + 1) + "." + tasks.get(idx).toString())
                .collect(Collectors.joining("\n"));

        return result;
    }

}
