package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * This command is for displaying the users current list of tasks
 */
public class ListCommand extends Command {
    /**
     * Uses the Ui object to display a list of the users tasks
     * @param tasklist TaskList that contains all the users current tasks.
     * @param ui Ui object for interaction with user
     * @param store Storage object that handles save and load functionality.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        ui.displayList(tasklist);
    }
}
