package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that can be executed or be an exit.
 */
public interface Command {
    /**
     * Executes command to be done.
     *
     * @param tasks    TaskList of Duke
     * @param ui      Ui of Duke
     * @param storage Storage of Duke
     * @throws DukeException Exception related to running the command
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns if the command is the Exit Command.
     *
     * @return Boolean of if it is an Exit Command
     */
    boolean isExit();

}
