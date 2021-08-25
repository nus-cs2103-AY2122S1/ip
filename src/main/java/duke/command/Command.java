package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

/**
 * Interface used to execute commands
 */
public interface Command {

    /**
     * Method to execute all commands
     *
     * @param taskList Manages all current tasks
     * @param ui Used to print messages
     * @param storage Loads and saves tasks to a txt file
     * @throws DukeException thrown if there are input/parsing errors
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Method to determine if Duke should stop running.
     *
     * @return true if the command is an exit command and false otherwise
     */
    boolean isExit();
}
