package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Creates a task list populated with tasks.
     *
     * @param tasks Tasks supplied to the task list.
     */
    public TaskList(List<Task> tasks) {
        list = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNo The index (1-based) of the task to be deleted.
     * @return The deleted task.
     */
    public Task delete(int taskNo) {
        return list.remove(--taskNo);
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves the task with the index given (1-based).
     *
     * @param taskNo The index of the task to be retrieved.
     * @return The task at the index given.
     */
    public Task get(int taskNo) {
        return list.get(--taskNo);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNo The index of the task to be marked as done (1-based).
     * @return true if the task was marked as done, false otherwise (i.e.
     * the task was already done).
     */
    public boolean markAsDone(int taskNo) {
        return list.get(--taskNo).markAsDone();
    }

    /**
     * Returns the string representation of the whole task list
     * that will be used to save to file. It is aggregated from
     * the format string of each task.
     *
     * @return The string representation for file saving purposes.
     */
    public List<String> formatData() {
        return list.stream().map(Task::formatForSave).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "You have no tasks!";
        } else {
            StringBuilder sb = new StringBuilder("Current tasks:\n");
            for (int i = 0; i < list.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, list.get(i).toString()));
            }
            sb.append("Total: ").append(list.size()).append(" tasks");
            return sb.toString();
        }
    }
}
