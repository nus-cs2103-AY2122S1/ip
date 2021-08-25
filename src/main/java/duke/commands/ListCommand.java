package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidDirectoryException;

/**
 * Tells Duke to reflect the most updated lists of tasks in the current session.
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Calls the Ui to output the tasks in the list.
     * @throws InvalidDirectoryException if the directory path is invalid as the list would not have been initialised.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws InvalidDirectoryException {
        try {
            ui.printList(task);
        } catch (NullPointerException npe) {
            throw new InvalidDirectoryException("   Unable to retrieve task info as specified directory does not exist");
        }
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
