package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a duke.command given by the user.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public abstract class Command {

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final boolean IS_EXIT;

    /**
     * Class constructor.
     */
    public Command() {
        IS_EXIT = false;
    }

    /**
     * Executes the given duke.command.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException Class
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether the duke.command is an exit duke.command.
     *
     * @return true if the duke.command is an exit duke.command, false otherwise.
     */
    public boolean isExit() {
        return IS_EXIT;
    }
}
