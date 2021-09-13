package duke.commands;

import java.util.function.Supplier;

import duke.TaskArrayList;
import duke.exceptions.DukeException;



/**
 * Command to execute when user types "delete"
 */
public class CommandDelete extends Command {
    public static final String HELP_COMMAND = "delete";
    public static final String HELP_DESCRIPTION = "Delete a task from the list";
    public static final String HELP_USAGE = "Usage: delete task_number\n"
            + "Delete a task\n"
            + "\ttask_number\ttask number of the task to delete";

    public CommandDelete(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isNotNumericArgument = () -> !cmdArgsArr[1].matches("[0-9]+");

        if (hasWrongArgumentCount || isNotNumericArgument.get()) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.deleteTask(Integer.parseInt(cmdArgsArr[1]));

    }
}
