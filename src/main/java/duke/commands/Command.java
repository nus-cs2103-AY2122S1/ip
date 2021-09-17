package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String representation of command output.
     * @throws DukeException Any exception relating specifically with Duke or its components.
     */
    public abstract String execute(TaskList taskList, CliUi cliUi, Storage storage) throws DukeException;

    /**
     * Returns boolean representing whether the specified command exits Duke. Will always return
     * False unless overwritten.
     *
     * @return False.
     */
    public boolean hasExited() {
        return false;
    }
}
