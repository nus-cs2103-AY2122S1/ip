package lania.command;

import lania.Log;
import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to view the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Diplays the user's list of tasks.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the list command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, Log log) {
        return ui.showListMessage(tasks);
    }
}
