package duke.commands;

import duke.Tasklist;
import duke.UI;
import duke.PersistentStorage;

/**
 * Class encapsulating a "list" command by the user
 */
public class ListAllCommand extends Command {

    public ListAllCommand(){}

    /**
     * Executes the list command by printing out all Tasks stored in the
     * Tasklist.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param ui The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     */
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        ui.listAllTasks(taskList);
    }
}
