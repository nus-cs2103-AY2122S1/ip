package duke.tasks;

import duke.data.TaskFileStorage;
import duke.data.TaskStorage;
import duke.exceptions.InvalidTaskNumberException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides functionality to manipulate tasks contained within the list.
 *
 * @author kevin9foong
 */
public class TaskList {
    private final List<Task> tasks;
    private TaskStorage taskStorage;

    /**
     * Constructs an instance of <code>TaskList</code> which persists data using the
     * provided <code>TaskStorage</code>.
     *
     * @param taskStorage provides functionality to persist task data.
     */
    public TaskList(TaskStorage taskStorage) throws IOException {
        this.taskStorage = taskStorage;
        tasks = taskStorage.loadTasks();
    }

    /**
     * Writes current taskList to the Task storage to be persisted.
     *
     * @param tasksToSave List of tasks to be saved to Task storage.
     * @throws IOException thrown when failure to write to Task storage occurs.
     */
    private void saveTasksToStorage(List<Task> tasksToSave) throws IOException {
        if (taskStorage == null) {
            taskStorage = new TaskFileStorage();
        }
        taskStorage.saveTasks(tasksToSave);
    }

    /**
     * Get task associated with index number in taskList.
     *
     * @param index index of task to get.
     * @return task associated with index number in taskList.
     * @throws InvalidTaskNumberException thrown when index provided exceeds valid index range.
     */
    public Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.get(index);
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task task to be added.
     * @return task that has been added to TaskList.
     * @throws IOException thrown when errors writing to storage occur.
     */
    public Task addTask(Task task) throws IOException {
        tasks.add(task);
        saveTasksToStorage(tasks);
        return task;
    }

    /**
     * Marks task as done and updates the <code>TaskStorage</code> file.
     *
     * @param index index of task to mark as done.
     * @return task that has been marked as done.
     * @throws IOException thrown when errors writing to storage occur.
     */
    public Task setDone(int index) throws IOException, InvalidTaskNumberException {
        Task doneTask = getTask(index);
        doneTask.setIsDone(true);
        saveTasksToStorage(tasks);
        return doneTask;
    }

    /**
     * Deletes task associated with given index number
     *
     * @param index index of task to delete
     * @return deleted task
     * @throws InvalidTaskNumberException thrown when index provided exceeds valid index range
     * @throws IOException                thrown when errors writing to storage occur
     */
    public Task deleteTask(int index) throws InvalidTaskNumberException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task removedTask = tasks.remove(index);
        saveTasksToStorage(tasks);
        return removedTask;
    }

    /**
     * Retrieves a new list containing all tasks in the taskList.
     *
     * @return new list containing copy of all tasks from the taskList.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets the number of tasks in the taskList.
     *
     * @return int representing number of tasks in the taskList.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

}
