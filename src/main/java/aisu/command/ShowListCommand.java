package aisu.command;

import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

/**
 * Command to display the tasklist in readable format.
 *
 * @author Liaw Xin Yan
 */
public class ShowListCommand extends Command {

    /**
     * Displays the tasklist.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        ui.showToUser("Here's what you have in your list:");
        System.out.println(tasklist);
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}