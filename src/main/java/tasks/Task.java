package tasks;

import data.TaskStorage;
import exceptions.InvalidTaskNumberException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a user-added task
 *
 * @author kevin9foong
 */
public abstract class Task {
    private static List<Task> taskList = new ArrayList<>();
    private static TaskStorage taskStorage;

    private String description;
    private boolean isDone = false;

    /**
     * Retrieves tasks from task storage into memory.
     *
     * @throws IOException thrown when failure to read from Task storage occurs.
     */
    public static void loadTasksFromStorage() throws IOException {
        Task.taskStorage = new TaskStorage();
        taskList = taskStorage.loadTasksFromFile();
    }

    /**
     * Writes current taskList to the Task storage to be persisted.
     *
     * @param tasksToSave List of tasks to be saved to Task storage
     * @throws IOException thrown when failure to write to Task storage occurs.
     */
    private static void saveTasksToStorage(List<Task> tasksToSave) throws IOException {
        if (taskStorage == null) {
            taskStorage = new TaskStorage();
        }
        taskStorage.saveTasksToFile(tasksToSave);
    }

    /**
     * Extracts data from given taskRepresentation and returns the specific subclass of
     * Task associated with the given String task representation.
     *
     * @param taskRepresentation comma separated String representation of a task
     * @return Task with data extracted from the given String representation of the Task
     */
    public static Task getTaskFromRepresentation(String taskRepresentation) {
        String[] taskData = taskRepresentation.split(",");
        boolean isDone = taskData[1].equals("X");

        switch (TaskType.valueOf(taskData[0])) {
        case DEADLINE:
            return new Deadline(taskData[2], isDone, taskData[3]);
        case EVENT:
            return new Event(taskData[2], isDone, taskData[3]);
        case TODO:
            return new ToDo(taskData[2], isDone);
        default:
            return null;
        }
    }

    /**
     * Get task associated with index number in taskList.
     *
     * @param index index of task to get
     * @return task associated with index number in taskList
     */
    public static Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskNumberException();
        }
        return taskList.get(index);
    }

    /**
     * Adds a new task to the taskList.
     *
     * @param task task to be added
     */
    public static Task addTask(Task task) throws IOException {
        taskList.add(task);
        saveTasksToStorage(taskList);
        return task;
    }

    /**
     * Deletes task associated with given index number
     *
     * @param index index of task to delete
     * @return deleted task
     */
    public static Task deleteTask(int index) throws InvalidTaskNumberException, IOException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskNumberException();
        }
        Task removedTask = taskList.remove(index);
        saveTasksToStorage(taskList);
        return removedTask;
    }

    /**
     * Retrieves a new list containing all tasks in the taskList.
     *
     * @return new list containing copy of all tasks from the taskList
     */
    public static List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }

    /**
     * Gets the number of tasks in the taskList.
     *
     * @return int representing number of tasks in the taskList
     */
    public static int getNumOfTasks() {
        return taskList.size();
    }

    /**
     * Marks task as done and updates the <code>TaskStorage</code> file.
     *
     * @throws IOException thrown when errors writing to file occur.
     */
    public void setDone() throws IOException {
        this.isDone = true;
        saveTasksToStorage(taskList);
    }

    /**
     * Set description of task.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Convert task data to representation to be saved in file.
     *
     * @return representation of task data
     */
    public String getTaskRepresentation() {
        return (isDone ? "X," : ",") + description + ",";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
