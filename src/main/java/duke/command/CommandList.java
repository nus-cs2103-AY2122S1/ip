package duke.command;

import duke.exception.DukeCommandException;
import duke.task.TaskList;

/**
 * Represents command to display the current task list.
 */
public class CommandList extends DukeCommand {
    /**
     * Displays the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.getTasks();
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
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("list");
        if (userArgs.length > 1) {
            throw new DukeCommandException("list");
        }

        return new CommandList();
    }
}
