package duke.storage;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Storage is in-charge of loading the saved taskList, and updating it.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public interface Storage {

    /**
     * Loads the file and initialises the TaskList.
     *
     * @return the list of tasks
     * @throws IOException if there is an issue with writing/reading the file
     */
    ArrayList<Task> load() throws IOException;

    /**
     * Changea the string in the storage file to indicate that the duke.task is done.
     *
     * @param index the position of the item
     * @throws IOException if there is an error writing to the file
     */
    void setDone(int index) throws IOException;

    /**
     * Adds a new duke.task to the storage file.
     *
     * @param type        the type of the duke.task
     * @param description the description of the duke.task
     * @param date        the date of the duke.task (if deadline or event)
     * @throws IOException if there is an error writing to the file
     */
    void add(String type, String description, String date) throws IOException;

    /**
     * Deletes the duke.task from the storage file.
     *
     * @param index the position of the duke.task in the list
     * @throws IOException if there is an error writing to the file
     */
    void delete(int index) throws IOException;
}
