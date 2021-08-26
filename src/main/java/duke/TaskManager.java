package duke;

import duke.task.Task;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a TaskManager object that stores and operates on a List of tasks.
 */
public class TaskManager {
    private List<Task> taskList;
    private final Storage storage = new Storage();

    TaskManager() throws DateTimeException {
        load();
    }

    /**
     * Adds a task to the taskList and saves it to database.
     *
     * @param task any Task object is accepted.
     */
    public void addTask(Task task) {
        taskList.add(task);
        save();
    }

    /**
     * Deletes a task at specified index.
     * If given index is too big or negative, an IllegalArgumentException is thrown.
     *
     * @param taskId The index (0-indexed) of the task to be deleted.
     * @return The task that has just been deleted.
     * @throws IllegalArgumentException If taskId >= taskList.size() or taskId < 0.
     */
    public Task deleteTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            Task ret = taskList.remove(taskId);
            save();
            return ret;
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    public int taskCount() {
        return taskList.size();
    }

    /**
     * Mark the task at specified index as done.
     * If given index is too big or negative, an IllegalArgumentException is thrown.
     *
     * @param taskId The index (0-indexed) of the task to be mark as done.
     * @return The task that has just been marked as done.
     * @throws IllegalArgumentException If taskId >= taskList.size() or taskId < 0.
     */
    public Task completeTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            taskList.set(taskId, taskList.get(taskId).done());
            save();
            return taskList.get(taskId);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    /**
     * Format the list of tasks in a ordered numbered list format.
     *
     * @return pretty-printable string of the list of tasks
     */
    public String listTasks() {
        return Stream.iterate(0, x -> x < taskList.size(), x -> x + 1)
                .map(x -> String.format("%d. %s", x + 1, taskList.get(x).toString()))
                .collect(Collectors.joining("\n"));
    }

    public List<Task> find(String keyword) {
        return this.taskList.stream().filter(t -> t.getDescriptions().contains(keyword)).collect(Collectors.toList());
    }

    private void load() throws DateTimeException {
        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
