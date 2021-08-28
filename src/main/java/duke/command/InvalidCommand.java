package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * InvalidCommand is a duke.command that is invalid.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class InvalidCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should not contain any description.
     */
    public InvalidCommand(String description) {
        super(description);
    }

    /**
     * Throws an exception.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     * @throws DukeException since the duke.command given is wrong
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Invalid duke.command.");
    }
}
