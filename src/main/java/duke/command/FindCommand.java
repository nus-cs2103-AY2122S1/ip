package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the find command of Duke.
 */
public class FindCommand extends Command{

    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Keyword to search for in TaskList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks if FindCommand is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the FindCommand.
     *
     * @param tasks TaskList object to find keyword from.
     * @param ui Ui Object to print to user.
     * @param storage Storage object which saves and loads the tasklist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList keywordTasks = tasks.findTasksWithKeyword(this.keyword);
        ui.printKeywordTasks(keywordTasks, this.keyword);
    }
}
