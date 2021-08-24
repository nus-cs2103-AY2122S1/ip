package service;

import exception.DukeException;
import exception.StorageException;
import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager class.
 *
 * This class acts as the manager of tasks held by the Duke.
 */
public class TaskList {

    // Error message formats.
    private static final String INVALID_TASK_ERROR_MESSAGE = "Task number '%d' is invalid.";
    private static final String FULL_CAPACITY_ERROR_MESSAGE = "Unable to execute as list is full.";
    private static final String EMPTY_LIST_ERROR_MESSAGE = "Unable to execute as list is empty.";

    private final static int MAX_STORAGE = 100;
      
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
     * Adds a task to the task list. Returns saved task.
     *
     * @param newTask task to save
     * @throws DukeException if task cannot be saved, due to full capacity of task list
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
}
