package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;



/**
 * Command to execute when user types "deadline".
 */
public class CommandDeadline extends Command {
    public static final String HELP_COMMAND = "deadline";
    public static final String HELP_DESCRIPTION = "Add a new deadline task";
    public static final String HELP_USAGE =
            "Usage: deadline task_name </by end_time>\n"
            + HELP_DESCRIPTION + "\n"
            + "\ttask_name\tname of the task to add\n"
            + "\t /by end_time\tdeadline of the deadline task";

    private static final int REQUIRED_ARG_COUNT = 2;

    public CommandDeadline(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT) || isMissingArgument("/by")) {
            throw new DukeException(HELP_USAGE);
        }

        String[] nameByPair = cmdArgsArr[1].split("/by", 2);
        Task toAdd = new Deadline(nameByPair[0], nameByPair[1]);
        return taskList.addTask(toAdd);
    }
}
