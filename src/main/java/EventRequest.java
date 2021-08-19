/**
 * EventRequest represents a request from the user to create an Event in the application.
 */
public class EventRequest extends TaskCollectionRequest {
    private static final String AT_DELIMITER = " /at ";

    private final Event event;

    /**
     * Creates a EventRequest.
     * @param taskCollection The target TaskCollection.
     * @param requestString The request String.
     * @throws UserException If the request String is invalid.
     */
    protected EventRequest(TaskCollection taskCollection, String requestString) throws UserException {
        super(taskCollection);
        this.event = EventRequest.parseEvent(requestString);
    }

    /**
     * Gets the Action the EventRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new AddTask(this.event, this.taskCollection);
    }

    /**
     * Parses an input String to create a Event into a Event.
     * @param eventString The input String.
     * @return The Event parsed from the input String.
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
