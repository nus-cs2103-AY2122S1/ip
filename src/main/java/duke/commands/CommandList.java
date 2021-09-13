package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;

/**
 * Command to execute when user types "list"
 */
public class CommandList extends Command {
    public static final String HELP_COMMAND = "list";
    public static final String HELP_DESCRIPTION = "List out all tracked tasks";
    public static final String HELP_USAGE = "Usage: list\n"
            + "List all tracked tasks";

    public CommandList(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }


    @Override
    public String run() throws DukeException {
        boolean hasWrongArgumentCount = cmdArgsArr.length > 1;

        if (hasWrongArgumentCount) {
            throw new DukeException("command list takes no arguments.");
        }

        return taskList.enumerate();
    }
}
