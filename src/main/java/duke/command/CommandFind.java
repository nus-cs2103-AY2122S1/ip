package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
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
    public String execute(TaskList tl) {
        return tl.searchKeyword(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input into the right format for the command
     *
     * @param userArgs Arguments to the command as provided by the user.
     */
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException, DukeArgumentException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("find");
        if (userArgs.length < 2) {
            throw new DukeCommandException("find");
        }

        assert userArgs.length == 2;

        if (userArgs[1].equals("")) {
            throw new DukeArgumentException("Search term cannot be empty.");
        } else if (userArgs[1].contains("|")) {
            throw new DukeArgumentException("Search term cannot contain \"|\".");
        }

        return new CommandFind(userArgs[1]);
    }
}
