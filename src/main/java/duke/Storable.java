package duke;

import duke.exception.DukeException;

/**
 * The Storable interface ensures that any class that implements it will have the methods
 * readTasksFromData and saveTasksToData.
 */
public interface Storable {

    /**
     * Reads tasks from a specified file.
     *
     * @return TaskList containing the tasks read from specified file.
     * @throws DukeException If task cannot be read from specified file.
     */
    TaskList readTasksFromData() throws DukeException;

    /**
     * Saves tasks to a specified file.
     *
     * @param taskList TaskList containing the tasks to be saved to specified file.
     * @throws DukeException If task cannot be saved to specified file.
     */
    void saveTasksToData(TaskList taskList) throws DukeException;
}
