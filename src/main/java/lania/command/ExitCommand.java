package lania.command;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Sets isExit to true and displays corresponding message.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @return The message displayed by executing the exit command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        this.isExit = true;
        return ui.showExitMessage();
    }
}
