package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * The abstract class for all user commands
 */
public abstract class Command {
    /**
     * Checks if the bot needs to exit
     *
     * @return whether the bot should exit
     */
    public abstract boolean isExit();

    /**
     * Executes the parsing of user input and the message
     * to print to the command line
     *
     * @param tasks the current list of tasks
     * @param ui the ui that interacts with the user
     * @param storage the place where the list of tasks will be stored
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
