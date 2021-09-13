package duke.commands;

import java.util.function.Supplier;

import duke.TaskArrayList;
import duke.exceptions.DukeException;



/**
 * Command to execute when user types "done"
 */
public class CommandDone extends Command {
    public static final String HELP_COMMAND = "done";
    public static final String HELP_DESCRIPTION = "Mark a task as done";
    public static final String HELP_USAGE = "Usage: done task_number\n"
            + "Mark a task as done\n"
            + "\ttask_number\ttask number of the task to mark as done";

    public CommandDone(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isNotNumericArgument = () -> !cmdArgsArr[1].matches("[0-9]+");

        if (hasWrongArgumentCount || isNotNumericArgument.get()) {
            throw new DukeException(HELP_USAGE);
        }

        return taskList.markDone(Integer.parseInt(cmdArgsArr[1]));
    }
}
