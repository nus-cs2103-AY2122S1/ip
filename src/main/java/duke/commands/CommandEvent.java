package duke.commands;

import java.util.function.Supplier;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Command to execute when user types "event"
 */
public class CommandEvent extends Command {
    public static final String HELP_COMMAND = "event";
    public static final String HELP_DESCRIPTION = "Add a new event task";
    public static final String HELP_USAGE = "Usage: event task_name </at event_time>\n"
            + "Add a new event task\n"
            + "\ttask_name\tname of the task to add\n"
            + "\t /at event_time\ttime that event occurs at";

    public CommandEvent(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isMissingAtArgument = () -> (cmdArgsArr[1].split("/at", 2).length != 2);

        if (hasWrongArgumentCount || isMissingAtArgument.get()) {
            throw new DukeException(Event.USAGE_TEXT);
        }

        String[] nameAtPair = cmdArgsArr[1].split("/at", 2);
        Task toAdd = new Event(nameAtPair[0], nameAtPair[1]);
        return taskList.addTask(toAdd);
    }
}
