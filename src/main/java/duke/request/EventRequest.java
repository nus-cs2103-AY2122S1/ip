package duke.request;

import duke.action.Action;
import duke.action.AddTask;
import duke.exception.UserException;
import duke.task.Event;
import duke.task.TaskCollection;

/**
 * duke.request.EventRequest represents a request from the user to create an duke.task.Event in the application.
 */
public class EventRequest extends TaskCollectionRequest {
    private static final String AT_DELIMITER = " /at ";

    private final Event event;

    /**
     * Creates a duke.request.EventRequest.
     * @param taskCollection The target duke.task.TaskCollection.
     * @param requestString The request String.
     * @throws UserException If the request String is invalid.
     */
    protected EventRequest(TaskCollection taskCollection, String requestString) throws UserException {
        super(taskCollection);
        this.event = EventRequest.parseEvent(requestString);
    }

    /**
     * Gets the Action the duke.request.EventRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new AddTask(this.event, this.taskCollection);
    }

    /**
     * Parses an input String to create a duke.task.Event into a duke.task.Event.
     * @param eventString The input String.
     * @return The duke.task.Event parsed from the input String.
     * @throws UserException If the eventString is invalid.
     */
    private static Event parseEvent(String eventString) throws UserException {
        String[] substrings = eventString.split(EventRequest.AT_DELIMITER, 2);

        if (substrings.length != 2) {
            String[] exceptionMessages = new String[]{
                    "A request to create an event task must follow the format:",
                    "  event <description> /by <datetime>"
            };
            throw new UserException(String.join(System.lineSeparator(), exceptionMessages));
        }

        String description = substrings[0];
        String atDateTime = substrings[1];
        return new Event(description, atDateTime);
    }
}
