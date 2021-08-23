package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This FindCommand class represents a command to find tasks using a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for a FindCommand instance that takes in a keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the matching tasks in the task list to the user.
     *
     * @param tasks The task list.
     * @param ui The UI of the application.
     * @param storage The storage system of the application.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksWithMatchingKeyword = tasks.findTasksWithKeyword(keyword);
        ui.showCommandDone("Here are the matching tasks in your list:\n" + tasksWithMatchingKeyword);
    }

    /**
     * Indicates that this command does not intend to exit the system.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
