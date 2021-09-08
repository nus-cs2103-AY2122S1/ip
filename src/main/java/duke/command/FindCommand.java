package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command for finding tasks using search strings.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String USAGE_MESSAGE = "To find a task with matching names, "
            + "use 'find [<search-string>...]'.";

    private final String commandArguments;

    public FindCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        String[] searchStrings = commandArguments.split("\\s+");
        return new DukeResponse(taskManager.list(searchStrings));
    }
}
