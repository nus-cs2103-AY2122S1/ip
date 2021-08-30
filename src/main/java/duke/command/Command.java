package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The class to represent user commands.
 */
public abstract class Command {

    /** Constructor of Command class */
    public Command() { }

    /**
     * Method to determine if the command is an exit command
     *
     * @return whether it is an exit command
     */
    public abstract boolean isExit();

    /**
     * Method to carry out the command
     *
     * @param tasks the list of tasks to be modified
     * @param ui the UI for the program
     * @param storage the storage utility for the program
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
