package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * The class to represent user commands.
 */
public abstract class Command {

    /** Constructor of Command class */
    public Command() { }

    /**
     * Determines if the command is an exit command.
     *
     * @return whether it is an exit command
     */
    public abstract boolean isExit();

    /**
     * Carries out the command.
     *
     * @param tasks the list of tasks to be modified
     * @param storage the storage utility for the program
     */
    public abstract String execute(TaskList tasks, Storage storage);
}
