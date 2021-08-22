package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Indicates that the user has given a command that is not understood. The distinction from exceptions in this project
 * is that exceptions are thrown if the user gives a command that is understood, but has problems.
 */
public class InvalidCommand extends Command {
    /**
     * Prints a message informing the user that their command was not understood.
     * @param tasklist TaskList that contains all the users current tasks.
     * @param ui Ui object for interaction with user
     * @param store Storage object that handles save and load functionality.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        ui.notifyBadCommand();
    }
}
