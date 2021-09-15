package tokio.commands;

import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Searches through task list and returns a new task list with items that match the keyword
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the extracting of task with the specified keyword in the task list.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @return String message for Tokio's reply.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findResult = tasks.findTask(keyword);
        return ui.printFindResults(findResult, keyword);
    }

    /**
     * Indicates that FindCommand is not terminating.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
