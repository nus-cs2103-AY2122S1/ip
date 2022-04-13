package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates an interface that requires a class that implements it to
 * be executable and check if it results in the termination of the program.
 *
 * @author Clifford
 */
public interface Command {
    /**
     * Executes the command and gives a String representation of the result of its execution.
     *
     * @param tasks list of task recorded by the user
     * @param ui user interface that interacts with user
     * @param storage the storage that contains the save file for the list of task
     * @return String representation of the result due to execution of command
     * @throws DukeException if something happens to the save file during
     *     file execution or invalid inputs by the user.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command terminates the program after its execution.
     *
     * @return true if this command terminates the program, false otherwise.
     */
    boolean isExit();
}
