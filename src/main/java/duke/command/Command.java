package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command class that handles user commands.
 *
 * @author Benjamin Lui
 */
public abstract class Command {

    Command() { }

    /**
     * Function for the children of the command class to implement when a command is executed.
     * @param tasks the list of tasks that duke.Duke has
     * @param ui the UI components available
     * @param storage the location to write the file back to (if necessary)
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
