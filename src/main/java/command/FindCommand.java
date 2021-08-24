package command;

import data.Storage;
import data.TaskList;
import data.Ui;

/**
 * Command that displays a filtered Tasklist when executed.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays a user's saved tasks containing the keyword.
     *
     * @param tasks The list of tasks that a user has
     * @param ui The ui that sends TaskList to the user
     * @param storage Not used for this command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.searchByKeyword(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
