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
    private static boolean IS_EXIT = true;
    /**
     * Class constructor.
     */
    public ExitCommand() {
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
        storage.store(tasks);
        return ui.showFarewell();
    }

    /**
     * Checks if this duke.command is an exit duke.command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        assert IS_EXIT : "IS_EXIT must be true";
        return IS_EXIT;
    }
}
