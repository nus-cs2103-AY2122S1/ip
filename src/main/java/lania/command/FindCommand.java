package lania.command;

import lania.Log;
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
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the find command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, Log log) {
        TaskList matchingTasks = tasks.find(keyword);
        return ui.showListMessage(matchingTasks);
    }
}
