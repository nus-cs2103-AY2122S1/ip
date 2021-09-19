package duke.logic.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;

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
        assert taskNo <= list.size() && taskNo > 0 : "Task number to be deleted out of bounds";
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
        assert taskNo <= list.size() && taskNo > 0 : "Task number to be retrieved out of bounds";
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
     * Updates a task with the new information supplied.
     *
     * @param taskNo The index of the task to be marked as done (1-based).
     * @param updateDescriptor New information to be used for the update.
     * @return The updated task.
     */
    public Task update(int taskNo, UpdateTaskDescriptor updateDescriptor) {
        assert updateDescriptor != null;
        Task t = this.get(taskNo);
        Task updatedTask = t.createUpdatedCopy(updateDescriptor);
        list.set(--taskNo, updatedTask); // replace old task with updated task
        return updatedTask;
    }

    /**
     * Finds all tasks whose descriptions contain one of the specified keywords.
     *
     * @param keywords The array of keywords to search for.
     * @return A list of resulting tasks.
     */
    public List<Task> find(String[] keywords) {
        assert keywords.length > 0;
        return list.stream()
                .filter(task -> Arrays.stream(keywords).anyMatch(task::containsKeyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns the string representation of the whole task list
     * that will be used to save to file. It is aggregated from
     * the format string of each task.
     *
     * @return The string representation for file saving purposes.
     */
    public List<String> getSaveFormat() {
        return list.stream().map(Task::getSaveFormat).collect(Collectors.toList());
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
            return sb.toString();
        }
    }
}
