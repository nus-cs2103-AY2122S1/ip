package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * A command to deal when an exception is thrown.
 */

public class ExceptionCommand extends Command {
    private DukeException e;

    /**
     * Creates an exception command to deal with exceptions that are thrown when parsing the users input.
     * @param e the DukeException that is thrown when the users input is parsed.
     */
    public ExceptionCommand(DukeException e) {
        this.e = e;
    }

    /**
     * Returns false since the ExceptionCommand is not an ExitCommand.
     * @return false since the ExceptionCommand is not an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command where the ui prints out the error message for the user to see.
     * @param tasks the TaskList where all the tasks are stored.
     * @param ui The ui to print out the user interface and to get input from user.
     * @param storage The storage that interacts with the file to save and load tasks.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printDukeException(e.getMessage());
    }
}
