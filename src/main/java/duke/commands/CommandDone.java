package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;



/**
 * Command to execute when user types "done"
 */
public class CommandDone extends Command {
    public static final String HELP_COMMAND = "done";
    public static final String HELP_DESCRIPTION = "Mark a task as done";
    public static final String HELP_USAGE =
            "Usage: done task_number\n"
            + HELP_DESCRIPTION + "\n"
            + "\ttask_number\ttask number of the task to mark as done";

    private static final int REQUIRED_ARG_COUNT = 2;

    public CommandDone(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT) || isNotNumericArgument(cmdArgsArr[1])) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.markDone(Integer.parseInt(cmdArgsArr[1]));
    }
}
