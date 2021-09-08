package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskManager;
import duke.util.DukeDateTime;

/**
 * Represents a command for adding a new <code>Event</code>.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String USAGE_MESSAGE = "To add a new event, use 'event <name> /at <event-timestamp>'.";

    private final String commandArguments;

    public AddEventCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        String[] eventDetails = commandArguments.split("\\s+/at\\s+", 2);
        if (eventDetails.length < 2) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        String eventName = eventDetails[0];
        DukeDateTime eventTimestamp = DukeDateTime.parseUserInputDateTime(eventDetails[1]);
        Event event = new Event(eventName, eventTimestamp);
        String message = taskManager.addTask(event);
        storage.saveTasks(taskManager);
        return new DukeResponse(message);
    }
}
