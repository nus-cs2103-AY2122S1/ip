package duke.command;

import duke.exception.DukeException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command for displaying help information and usage of the different commands.
 */
public class HelpCommand extends Command {
    private static final String[] ALL_USAGES = {
        LoadCommand.USAGE_MESSAGE,
        AddToDoCommand.USAGE_MESSAGE,
        AddDeadlineCommand.USAGE_MESSAGE,
        AddEventCommand.USAGE_MESSAGE,
        ListCommand.USAGE_MESSAGE,
        FindCommand.USAGE_MESSAGE,
        CompleteTaskCommand.USAGE_MESSAGE,
        DeleteTaskCommand.USAGE_MESSAGE,
        ExitCommand.USAGE_MESSAGE
    };

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        return new DukeResponse(String.join("\n", ALL_USAGES));
    }
}
