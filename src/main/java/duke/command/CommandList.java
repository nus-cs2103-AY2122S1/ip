package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.ui.Ui;

/**
 * The class that models a list command.
 */
public class CommandList extends Command {

    /**
     * Execute list action by printing string representation of the taskList.
     *
     * @param tasks   The taskList to be printed.
     * @param ui      The Ui object to print messages.
     * @param storage The Storage object that auto-saves after modification.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Here are the tasks in your list:" + "\n"
                + tasks.toString();
    }
}
