package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * ByeCommand is a duke.command which closes the Duke bot and its readers.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class ByeCommand extends Command {

    /**
     * Constructor.
     *
     * @param description empty string
     */
    public ByeCommand(String description) {
        super(description);
    }

    /**
     * Closes the Ui and all its readers.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
    }

    /**
     * Returns true if this duke.command is executed.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
