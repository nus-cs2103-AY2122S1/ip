package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskManager;
import duke.util.DukeDateTime;

/**
 * Represents a command for adding a new <code>Deadline</code>.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String USAGE_MESSAGE = "To add a new deadline, use 'deadline <name> /by <due-date>'.";
    private static final String SPLIT_REGEX = "\\s+/by\\s+";
    private static final int ARGUMENTS_LENGTH = 2;

    private final String commandArguments;

    public AddDeadlineCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        String[] deadlineDetails = commandArguments.split(SPLIT_REGEX, ARGUMENTS_LENGTH);
        if (deadlineDetails.length < ARGUMENTS_LENGTH) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        String deadlineName = deadlineDetails[0];
        DukeDateTime deadlineDueDate = DukeDateTime.parseUserInputDateTime(deadlineDetails[1]);
        Deadline deadline = new Deadline(deadlineName, deadlineDueDate);
        String message = taskManager.addTask(deadline);
        storage.saveTasks(taskManager);
        return new DukeResponse(message);
    }
}
