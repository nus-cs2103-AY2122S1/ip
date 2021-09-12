package tokio.commands;

import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Exits Tokio and shows a bye message.
 */
public class ByeCommand extends Command {
    /**
     * Outputs bye message.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @return String message for Tokio's reply.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printBye();
    }

    /**
     * Indicates that Bye command is terminating.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
