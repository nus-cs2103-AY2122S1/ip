package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Command to execute when user types "event".
 */
public class CommandEvent extends Command {
    public static final String HELP_COMMAND = "event";
    public static final String HELP_DESCRIPTION = "Add a new event task";
    public static final String HELP_USAGE =
            "Usage: event task_name </at event_time>\n"
            + HELP_DESCRIPTION + "\n"
            + "\ttask_name\tname of the task to add\n"
            + "\t /at event_time\ttime that event occurs at";

    private static final int REQUIRED_ARG_COUNT = 2;

    public CommandEvent(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT) || isMissingArgument("/at")) {
            throw new DukeException(HELP_USAGE);
        }

        String[] nameAtPair = cmdArgsArr[1].split("/at", 2);
        Task toAdd = new Event(nameAtPair[0], nameAtPair[1]);
        return taskList.addTask(toAdd);
    }
}
