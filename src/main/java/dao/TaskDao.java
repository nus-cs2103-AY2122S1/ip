package dao;

import model.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * Data access object (dao) for the Task model.
 */
public interface TaskDao {
    /**
     * Adds a task to the data layer, throw error instead of Optional if add fails.
     *
     * @param task Task to be added.
     */
    void addTask(Task task);
    
    /**
     * Deletes the task using 0-indexing.
     * Returns null if reading or writing the file task fails.
     *
     * @param index Index as it appears in the list - 1 (from 1 indexing to 0 indexing).
     */
    Task deleteTask(int index) throws IndexOutOfBoundsException;
    
    /**
     * Marks a task as done.
     *
     * @param index Index as it appears in the list - 1 (from 1 indexing to 0 indexing).
     */
    void markDone(int index) throws IndexOutOfBoundsException;
    
    /**
     * Gets a single task from the task list.
     * Return null if the task file containing the array list cannot be read.
     *
     * @param index Index as it appears in the list - 1 (from 1 indexing to 0 indexing).
     * @return Task or null if reading the file fails.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    Task getTask(int index) throws IndexOutOfBoundsException;
    
    /**
     * Gets all the tasks from the list.
     * Returns empty list if reading the file fails.
     *
     * @return Tasks ArrayList of all the tasks in the list.
     */
    List<Task> getAll();
    
    /**
     * Gets all the tasks from the list filtered by keyword.
     * Returns empty list if reading the file fails.
     *
     * @return Tasks ArrayList of the tasks filtered by keyword.
     */
    List<Task> getByKeyword(String keyword);
    
    /**
     * Gets all the tasks from the list filtered by the time (if the task is a TimedItem).
     *
     * @return Tasks ArrayList of the tasks filtered by time.
     */
    List<Task> getByTiming(LocalDate time);
    
    /**
     * Gets the size of the current list.
     * Returns -1 if the Task file cannot be read or invalid.
     *
     * @return Size of the current list or -1.
     */
    int getSize();
}
