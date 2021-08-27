package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.Ui;

/**
 * This is an abstract class of all the Commands that Duke can perform.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public abstract class Command {

    protected final String description;

    /**
     * Constructor.
     *
     * @param description it should contain more instructions for the duke.command
     */
    public Command(String description) {
        this.description = description;
    }

    /**
     * Adds the relevant actions pertaining to the duke.command.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if it is a bye duke.command and returns false otherwise
     *
     * @return boolean of whether this is the last duke.command or not
     */
    public boolean isExit() {
        return false;
    }
}

