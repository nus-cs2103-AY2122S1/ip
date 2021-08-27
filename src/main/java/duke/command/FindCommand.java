package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents the command when a user inputs "find".
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Constructor for find, which takes in the keyword to be found.
     *
     * @param keyword keyword to be searched for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for the keyword in the task list.
     *
     * @param tasks task list.
     * @param storage storage.
     * @param ui ui.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.searchTask(keyword);
    }

    /**
     * Returns false as the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
