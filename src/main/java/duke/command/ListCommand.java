package duke.command;

import duke.exception.DukeException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.util.DukeDateTime;

/**
 * Represents a command for listing tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String USAGE_MESSAGE =
            "To list all tasks, use 'list'.\nTo list all tasks on a certain date, use 'list <date>'.";

    private final String commandArguments;

    public ListCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            return new DukeResponse(taskManager.list());
        } else {
            DukeDateTime date = DukeDateTime.parseUserInputDate(commandArguments);
            return new DukeResponse(taskManager.list(date));
        }
    }
}
