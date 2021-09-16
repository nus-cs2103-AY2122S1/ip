package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;

/**
 * Command to execute when user types "delete"
 */
public class CommandDelete extends Command {
    public static final String HELP_COMMAND = "delete";
    public static final String HELP_DESCRIPTION = "Delete a task from the list";
    public static final String HELP_USAGE =
            "Usage: delete task_number\n"
            + HELP_DESCRIPTION + "\n"
            + "\ttask_number\ttask number of the task to delete";

    private static final int REQUIRED_ARG_COUNT = 2;

    public CommandDelete(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT) || isNotNumericArgument(cmdArgsArr[1])) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.deleteTask(Integer.parseInt(cmdArgsArr[1]));

    }
}
