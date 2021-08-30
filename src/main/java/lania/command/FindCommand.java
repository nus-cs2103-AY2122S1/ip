package lania.command;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to find tasks matching the given input.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays tasks that match the given keyword.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        TaskList temp = tasks.find(keyword);
        ui.showListMessage(temp);
    }
}
