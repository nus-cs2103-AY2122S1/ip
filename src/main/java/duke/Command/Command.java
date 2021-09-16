package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Executes command based on which command it is
     *
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return Confirmation message
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if Command is ExitCommand
     *
     * @return true if ExitCommand
     */
    public abstract boolean isExit();
}
