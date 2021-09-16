package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;

/**
 * Command to execute when user types "list"
 */
public class CommandList extends Command {
    public static final String HELP_COMMAND = "list";
    public static final String HELP_DESCRIPTION = "List out all tracked tasks";
    public static final String HELP_USAGE =
            "Usage: list\n"
            + HELP_DESCRIPTION;

    private static final int REQUIRED_ARG_COUNT = 1;

    public CommandList(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT)) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.enumerate();
    }
}
