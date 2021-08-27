package main.java.duke.tasklist;

import main.java.duke.Ui;
import main.java.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TaskList stores the list of tasks.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public interface TaskList {

    /**
     * Adds the task into the list.
     *
     * @param task the task to be added into the list
     */
    void addTask(Task task);

    /**
     * Setter to change the done status of the task.
     */
    void setDone(int index);

    /**
     * Delete the task at a specified index.
     */
    void delete(int index);

    /**
     * Prints the list.
     */
    void printList();

    /**
     * Prints Tasks on the specified date.
     */
     void printListDate(String date);

    /**
     * Return the number of items in the TaskList.
     *
     * @return listCount
     */
    int count();

    /**
     * Prints Tasks containing the specified searchString.
     * @param searchString the string to be used for searching
     */
    void printListSearch(String searchString);
}
