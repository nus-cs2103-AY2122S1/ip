package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;

/**
 * Command to execute when user types "find"
 */
public class CommandFind extends Command {
    public static final String HELP_COMMAND = "find";
    public static final String HELP_DESCRIPTION = "Find matching tasks from the list using a keyphrase";
    public static final String HELP_USAGE =
            "Usage: find keyphrase\n"
            + HELP_DESCRIPTION + "\n"
            + "\tkeyphrase\tkeyphrase to search for among tasks";

    private static final int REQUIRED_ARG_COUNT = 2;

    public CommandFind(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT)) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.find(cmdArgsArr[1]);
    }
}
