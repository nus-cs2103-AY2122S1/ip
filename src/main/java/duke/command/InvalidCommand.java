package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.utils.DukeException;

/**
 * InvalidCommand is a command that is invalid.
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
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException since the command given is wrong
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        throw new DukeException("Invalid command.");
    }
}
