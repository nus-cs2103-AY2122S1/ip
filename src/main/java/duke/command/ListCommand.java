package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The list command.
 */
public class ListCommand extends Command {

    /**
     * Constructs the list command.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @return The output of executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Here are the tasks in your list:\n" + tasks;
    }

}
