package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This command shows the welcome message.
 */
public class WelcomeCommand extends Command {
    /**
     * Shows the welcome message.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return the welcome message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return Ui.showWelcome();
    }
}
