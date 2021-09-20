package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Unknown command.
 */
public class UnknownCommand extends Command {
    /**
     * Returns an unknown command error message.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @throws DukeException As the command is unrecognised.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-( "
                + "Type \"help\" for a list of commands I understand.");
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
