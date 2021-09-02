package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Interface used to execute commands.
 */
public interface Command {

    /**
     * Execute all commands.
     *
     * @param taskList Manages all current tasks.
     * @param ui Used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean true If the command is an exit command and false otherwise.
     */
    boolean isExit();
}
