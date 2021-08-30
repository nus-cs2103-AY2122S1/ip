package lania.command;

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
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showListMessage(tasks);
    }
}
