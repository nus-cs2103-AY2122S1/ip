package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.storage.Storage;
import main.java.duke.tasklist.TaskList;
import main.java.duke.Ui;

/**
 * InvalidCommand is a command that is invalid.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
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
     * Simply throws an exception.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        throw new DukeException("Invalid command.");
    }
}
