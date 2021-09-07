package duke.command;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a duke.command to exit Duke.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class ExitCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final boolean IS_EXIT;

    /**
     * Class constructor.
     */
    public ExitCommand() {
        IS_EXIT = true;
    }

    /**
     * Executes the duke.command to exit the chat bot, stores tasks into storage.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException class
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.store(tasks);
            ui.showFarewell();
        } catch (IOException e) {
            throw new DukeException(e);
        }
    }

    /**
     * Checks if this duke.command is an exit duke.command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }
}
