package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command for deleting a <code>Task</code>.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String USAGE_MESSAGE = "To delete a task, use 'delete <task-number>'.";

    private final String commandArguments;

    public DeleteTaskCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        try {
            int taskNumber = Integer.parseInt(commandArguments);
            String message = taskManager.deleteTask(taskNumber);
            storage.saveTasks(taskManager);
            return new DukeResponse(message);
        } catch (NumberFormatException e) {
            // User provided an argument that is not parsable.
            throw new DukeException("Invalid task number.");
        }
    }
}
