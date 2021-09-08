package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command for marking a <code>Task</code> as completed.
 */
public class CompleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String USAGE_MESSAGE = "To mark a task as done, use 'done <task-number>'.";

    private final String commandArguments;

    public CompleteTaskCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        try {
            int taskNumber = Integer.parseInt(commandArguments);
            String message = taskManager.markTaskAsDone(taskNumber);
            storage.saveTasks(taskManager);
            return new DukeResponse(message);
        } catch (NumberFormatException e) {
            // User provided an argument that is not parsable.
            throw new DukeException("Invalid task number.");
        }
    }
}
