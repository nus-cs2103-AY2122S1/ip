package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class abstracts the list command that the user wants to execute.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Execute the command to show the list to the user.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
