package duke.commands;

import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

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
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the result of the List operation.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage) {
        return new CommandResult(response.listAllTasks(taskList));
    }
}
