package kayu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import kayu.exception.KayuException;
import kayu.task.Task;

/**
 * Manages {@link kayu.task.Task}s held by the {@link kayu.Kayu}.
 */
public class TaskList {

    // Error message formats.
    protected static final String ERROR_INVALID_TASK = "Task number '%d' is invalid.";
    protected static final String ERROR_EMPTY_LIST = "Unable to execute as list is empty.";
    protected static final String ERROR_TASK_ALREADY_DONE = "Task number '%d' is already done.";

    private final List<Task> tasks = new ArrayList<>();

    /**
     * Returns the List of {@link kayu.task.Task}s.
     *
     * @return A list of {@link kayu.task.Task}s.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Initializes the {@link #tasks} list with the specified {@link kayu.task.Task} list.
     *
     * @param tasks List of {@link kayu.task.Task} to initialise {@link #tasks} with.
     */
    public void initializeTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    /**
     * Gets the number of {@link kayu.task.Task}s current stored.
     *
     * @return Number of tasks stored currently.
     */
    public int getCurrentCapacity() {
        return tasks.size();
    }

    /**
     * Adds a {@link kayu.task.Task} to the {@link #tasks} list.
     * Returns saved {@link kayu.task.Task}.
     *
     * @param newTask {@link kayu.task.Task} to save.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Updates a {@link kayu.task.Task} to 'done' based on the input <code>taskNumber</code>
     * number fed as a String. Provides an output message on return.
     *
     * @param taskNumber String format of the {@link kayu.task.Task} number to delete.
     * @return String message of successful completion marking of {@link kayu.task.Task}.
     * @throws KayuException If <code>taskNumber</code> is not valid.
     */
    public Task updateTaskAsDone(int taskNumber) throws KayuException {
        Task selectedTask = getTaskByNumber(taskNumber);
        if (selectedTask.isDone()) {
            throw new KayuException(String.format(ERROR_TASK_ALREADY_DONE, taskNumber));
        }
        selectedTask.markAsDone();
        return selectedTask;
    }

    /**
     * Deletes a {@link kayu.task.Task} based on the input <code>taskNumber</code> fed as a String.
     * Provides an output message on return.
     *
     * @param taskNumber String format of the {@link kayu.task.Task} number to delete.
     * @return String message of successful deletion of {@link kayu.task.Task}.
     * @throws KayuException If <code>taskNumber</code> is not valid.
     */
    public Task deleteTask(int taskNumber) throws KayuException {
        Task selectedTask = getTaskByNumber(taskNumber);
        tasks.remove(selectedTask);
        return selectedTask;
    }

    /**
     * Gets the {@link kayu.task.Task} based on the input <code>taskNumber</code>.
     *
     * @param taskNumber {@link kayu.task.Task} number to obtain.
     * @return Associated {@link kayu.task.Task}.
     * @throws KayuException If the {@link #tasks} List is empty or
     * the <code>taskNumber</code> is not valid.
     */
    private Task getTaskByNumber(int taskNumber) throws KayuException {
        if (tasks.isEmpty()) {
            throw new KayuException(ERROR_EMPTY_LIST);
        }
        if (taskNumber <= 0 || tasks.size() < taskNumber) {
            throw new KayuException(String.format(ERROR_INVALID_TASK, taskNumber));
        }
        return tasks.get(taskNumber - 1); // shift to 0-indexing
    }

    /**
     * Returns a Map of {@link kayu.task.Task} and their numberings based on
     * the <code>keyword</code> parameter.
     *
     * @param keywords Keyword Strings to find in {@link kayu.task.Task}s.
     * @return A Map of {@link kayu.task.Task} that has similar description to <code>keyword</code>.
     */
    public Map<Integer, Task> findTasksByKeywords(String... keywords) {
        SortedMap<Integer, Task> taskMap = new TreeMap<>();

        for (String key: keywords) {
            addTasksToSearchMap(taskMap, key);
        }
        return taskMap;
    }

    private void addTasksToSearchMap(Map<Integer, Task> taskMap, String key) {
        if (key.isBlank()) {
            return; // in case parsed keywords has blanks
        }

        key = key.toLowerCase(Locale.ROOT);
        for (int idx = 0; idx < tasks.size(); idx++) {
            Task task = tasks.get(idx);
            String desc = task.getDescription().toLowerCase(Locale.ROOT);
            if (desc.contains(key)) {
                taskMap.put(idx, task);
            }
        }
    }
}
