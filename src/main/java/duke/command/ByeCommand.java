package main.java.duke.command;

import main.java.duke.storage.Storage;
import main.java.duke.tasklist.TaskList;
import main.java.duke.Ui;

/**
 * ByeCommand is a command which closes the Duke bot and its readers.
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
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
    }

    /**
     * Returns true if this command is executed.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
