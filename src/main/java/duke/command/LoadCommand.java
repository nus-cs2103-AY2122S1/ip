package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command for loading existing tasks from a specified file.
 */
public class LoadCommand extends Command {
    public static final String COMMAND_WORD = "load";
    public static final String USAGE_MESSAGE = "To load tasks from a file, use 'load <relative-file-path>'.";

    private final String commandArguments;

    public LoadCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        storage.setFilePath(commandArguments);
        taskManager.setTaskList(storage.loadTasks());
        return new DukeResponse("File successfully loaded! " + storage.getTasksLoadedMessage(taskManager));
    }
}
