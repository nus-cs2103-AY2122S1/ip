package duke.command;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * Represents a duke.command to exit Duke.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class ExitCommand extends Command {
    private static final boolean IS_EXIT = false;
    /**
     * Class constructor.
     */
    public ExitCommand() {
        assert isExit() : "isExist must return true";
    }

    /**
     * Executes the duke.command to exit the chat bot, stores tasks into storage.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException class
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.store(tasks);
            return ui.showFarewell();
        } catch (IOException e) {
            throw new DukeException(DukeException.Exceptions.IOException);
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
