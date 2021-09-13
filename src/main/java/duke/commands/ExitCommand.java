package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to shut down the chat-bot.
 */
public class ExitCommand extends Command {

    /**
     * Performs the required actions that will print a bye message and terminate the program.
     *
     * @param tasks   the full task list containing all the tasks.
     * @param ui      the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }
}
