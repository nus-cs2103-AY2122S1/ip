package duke.command;

import duke.DukeUi;
import duke.task.TaskList;

/**
 * Represents a command to search the task list.
 */
public class CommandFind extends DukeCommand {
    private String keyword;

    /**
     * Creates a new CommandFind.
     *
     * @param keyword Keyword to be used in the search.
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches the task list using the keyword.
     *
     * @param tl Task list for the user.
     */
    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.searchKeyword(this.keyword));
    }

    /**
     * As described in DukeCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
