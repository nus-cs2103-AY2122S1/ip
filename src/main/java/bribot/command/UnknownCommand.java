package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents an unknown command that is given by the user.
 */

public class UnknownCommand extends Command {

    /**
     * Returns false as the command is not an exit command.
     * @return false as the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the command, the ui will print out to the user that the program does not understand
     * the given input.
     * @param tasks the TaskList where all the tasks are stored.
     * @param ui The ui to print out the user interface and to get input from user.
     * @param storage The storage that interacts with the file to save and load tasks.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printUnknownCommand();
    }
}
