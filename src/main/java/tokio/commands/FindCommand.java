package tokio.commands;

import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Command that search through task list and returns a new task list with items that match the keyword
 */
public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findResult = tasks.findTask(keyword);
        return ui.printFindResults(findResult, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
