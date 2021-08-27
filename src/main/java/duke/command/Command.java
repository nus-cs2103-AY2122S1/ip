package main.java.duke.command;

import main.java.duke.storage.Storage;
import main.java.duke.tasklist.TaskList;
import main.java.duke.Ui;

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
     * @param description it should contain more instructions for the command
     */
    public Command(String description) {
        this.description = description;
    }

    /**
     * Adds the relevant actions pertaining to the command.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if it is a bye command and returns false otherwise
     *
     * @return boolean of whether this is the last command or not
     */
    public boolean isExit() {
        return false;
    }
}

