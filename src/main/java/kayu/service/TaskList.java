package kayu.service;

import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TaskManager class.
 *
 * This class acts as the manager of tasks held by the Duke.Duke.
 */
public class TaskList {

    // Error message formats.
    protected static final String INVALID_TASK_ERROR_MESSAGE = "Task number '%d' is invalid.";
    protected static final String FULL_CAPACITY_ERROR_MESSAGE = "Unable to execute as list is full.";
    protected static final String EMPTY_LIST_ERROR_MESSAGE = "Unable to execute as list is empty.";

    protected final static int MAX_STORAGE = 100;
      
    private final List<Task> tasks = new ArrayList<>();
    
    public List<Task> getTasks() {
        return tasks;
    }

    public void init(List<Task> taskList) throws StorageException {
        if (taskList.size() > MAX_STORAGE) {
            throw new StorageException(FULL_CAPACITY_ERROR_MESSAGE);
        }
        this.tasks.clear();
        this.tasks.addAll(taskList);
    }

    /**
     * Gets the current number of tasks stored.
     *
     * @return number of tasks stored currently
     */
    public int getCapacity() {
        return tasks.size();
    }

    /**
     * Adds a Duke.task to the Duke.task list. Returns saved Duke.task.
     *
     * @param newTask Duke.task to save
     * @throws DukeException if Duke.task cannot be saved, due to full capacity of Duke.task list
     */
    public void addTask(Task newTask) throws DukeException {
        if (tasks.size() >= MAX_STORAGE) {
            throw new DukeException(FULL_CAPACITY_ERROR_MESSAGE);
        }
        tasks.add(newTask);
    }

    /**
     * Update a Task completed based on the input Task number fed as a String.
     * Provides an output message on return.
     *
     * @param taskNumber String format of the Task number to delete
     * @return String message of successful completion marking of Task
     * @throws DukeException if the Task number is not valid
     */
    public Task updateTaskAsDone(int taskNumber) throws DukeException {
        Task selectedTask = getTaskByNumber(taskNumber);
        selectedTask.markAsDone();
        return selectedTask;
    }

    /**
     * Deletes a Task based on the input Task number fed as a String.
     * Provides an output message on return.
     *
     * @param taskNumber String format of the Task number to delete
     * @return String message of successful deletion of Task
     * @throws DukeException if the Task number is not valid
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        Task selectedTask = getTaskByNumber(taskNumber);
        tasks.remove(selectedTask);
        return selectedTask;
    }

    /**
     * Gets the Task based on the input Task number fed as a String.
     *
     * @param taskNumber String format of the Task number to obtain
     * @return associated Task
     * @throws DukeException if the Task List is empty or the Task number is not valid
     */
    private Task getTaskByNumber(int taskNumber) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException(EMPTY_LIST_ERROR_MESSAGE);
        }
        if (taskNumber <= 0 || tasks.size() < taskNumber) {
            throw new DukeException(String.format(INVALID_TASK_ERROR_MESSAGE, taskNumber));
        }
        return tasks.get(taskNumber - 1); // shift to 0-indexing
    }

    /**
     * Returns a Map of {@link kayu.task.Task} and their numberings based on 
     * the <code>keyword</code> parameter.
     * 
     * @param keyword Keyword String to find in {@link kayu.task.Task}s.
     * @return A Map of {@link kayu.task.Task} that has similar description to <code>keyword</code>.
     */
    public Map<Integer, Task> findMatchingTasks(String keyword) {
        Map<Integer, Task> taskMap = new HashMap<>();
        
        for (int idx = 0; idx < tasks.size(); idx ++) {
            Task task = tasks.get(idx);
            if (task.getDescription().contains(keyword)) {
                taskMap.put(idx, task);
            }
        }
        return taskMap;
    }
}
