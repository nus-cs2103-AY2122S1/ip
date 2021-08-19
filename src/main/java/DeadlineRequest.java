/**
 * DeadlineRequest represents a request from the user to create a Deadline in the application.
 */
public class DeadlineRequest extends TaskCollectionRequest {
    private static final String BY_DELIMITER = " /by ";

    private final Deadline deadline;

    /**
     * Creates a DeadlineRequest.
     * @param taskCollection The target TaskCollection.
     * @param requestString The request String.
     */
    protected DeadlineRequest(TaskCollection taskCollection, String requestString) {
        super(taskCollection);
        this.deadline = DeadlineRequest.parseDeadline(requestString);
    }

    /**
     * Gets the Action the DeadlineRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new AddTask(this.deadline, this.taskCollection);
    }

    /**
     * Parses an input String to create a Deadline into a Deadline.
     * @param deadlineString The input String.
     * @return The Deadline parsed from the input String.
     */
    private static Deadline parseDeadline(String deadlineString) {
        String[] substrings = deadlineString.split(DeadlineRequest.BY_DELIMITER, 2);
        String description = substrings[0];
        String byDateTime = substrings[1];
        return new Deadline(description, byDateTime);
    }
}
