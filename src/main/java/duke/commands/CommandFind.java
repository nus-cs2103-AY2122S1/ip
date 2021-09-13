package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;

/**
 * Command to execute when user types "find"
 */

public class CommandFind extends Command {
    public static final String HELP_COMMAND = "find";
    public static final String HELP_DESCRIPTION = "Find matching tasks from the list";
    public static final String HELP_USAGE = "Usage: find keyphrase\n"
            + "Search for tasks matching the keyphrase\n"
            + "\tkeyphrase\tkeyphrase to search for among tasks";


    public CommandFind(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);

        if (hasWrongArgumentCount) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.find(cmdArgsArr[1]);
    }
}
