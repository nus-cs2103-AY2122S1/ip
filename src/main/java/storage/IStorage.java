package storage;

import exception.InvalidIndexException;
import models.Task;
import tasklist.TaskList;

/**
 * Interface for the Storage object which saves and loads TaskList from local file.
 */
public interface IStorage {

    /**
     * Adds task to the TaskList.
     *
     * @param task Task to be added to the storage.
     */
    void addTask(Task task);

    /**
     * Sets a specified Task inside TaskList to be done.
     *
     * @param index Index of the Task that will be set done.
     * @throws InvalidIndexException If there is no task with the specified index.
     */
    void setDone(int index) throws InvalidIndexException;

    /**
     * Gets a specified Task from TaskList.
     *
     * @param index Index of the Task that will be retrieved.
     * @return  Task object with the specified index.
     */
    Task getTask(int index);

    /**
     * Gets the latest Task from the TaskList.
     *
     * @return Task object with the last index.
     */
    Task getLastTask();

    /**
     * Deletes Task from the TaskList with the specified index.
     *
     * @param index Index of the Task that will be deleted.
     * @return String representation of the deleted Task.
     * @throws InvalidIndexException If there is no Task with the specified index.
     */
    String deleteTask(int index) throws InvalidIndexException;


    /**
     * Gets the number of Task in the TaskList.
     *
     * @return The number of Task objects in the TaskList.
     */
    int getSize();

    TaskList findKeyword(String ... keywords);

    TaskList getPrevious();

    TaskList getNext();

    void undo();

    void redo();
}
