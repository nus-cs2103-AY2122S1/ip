package storage;

import exception.DukeException;
import models.Task;
import tasklist.TaskList;

/**
 * Interface for the Storage object which saves and loads TaskList from local file.
 */
public interface IStorage {

    /**
     * Wrapper function that add task to the TaskList.
     *
     * @param task
     */
    public void addTask(Task task);

    /**
     * Wrapper function that set a specified Task inside TaskList to be done.
     *
     * @param index Index of the Task that will be set done.
     * @throws DukeException If there is no task with the specified index.
     */
    public void setDone(int index) throws DukeException;

    /**
     * Wrapper function that get a specified Task from TaskList.
     *
     * @param index Index of the Task that will be retrieved.
     * @return  Task object with the specified index.
     */
    public Task getTask(int index);

    /**
     * Wrapper function to get the latest Task from the TaskList.
     *
     * @return Task object with the last index.
     */
    public Task getLastTask();

    /**
     * Wrapper function that delete Task from the TaskList with the specified index.
     *
     * @param index Index of the Task that will be deleted.
     * @return String representation of the deleted Task.
     * @throws DukeException If there is no Task with the specified index.
     */
    public String deleteTask(int index) throws DukeException;


    /**
     * Wrapper function to get the number of Task in the TaskList.
     *
     * @return The number of Task objects in the TaskList.
     */
    public int getSize();

    public TaskList findKeyword(String keyword);
}
