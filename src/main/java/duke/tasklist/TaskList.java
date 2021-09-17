package duke.tasklist;

import duke.task.Task;

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
    String addTask(Task task);

    /**
     * Setter to change the done status of the task.
     */
    String setDone(int index);

    /**
     * Delete the task at a specified index.
     */
    String delete(int index);

    /**
     * Prints the list.
     */
    String printList();

    /**
     * Prints Tasks on the specified date.
     */
    String printListDate(String date);

    /**
     * Returns the number of items in the TaskList.
     *
     * @return listCount
     */
    int count();

    /**
     * Prints Tasks containing the specified searchString.
     * @param searchString the string to be used for searching
     */
    String printListSearch(String searchString);
}
