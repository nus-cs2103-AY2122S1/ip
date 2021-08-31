package duke.request;

import duke.action.Action;
import duke.action.AddTask;
import duke.exception.UserException;
import duke.task.Deadline;
import duke.task.TaskCollection;

/**
 * duke.request.DeadlineRequest represents a request from the user to create a duke.task.Deadline in the application.
 */
public class DeadlineRequest extends TaskCollectionRequest {
    private static final String BY_DELIMITER = " /by ";

    private final Deadline deadline;

    /**
     * Creates a duke.request.DeadlineRequest.
     * @param taskCollection The target duke.task.TaskCollection.
     * @param requestString The request String.
     * @throws UserException If the request String is invalid.
     */
    protected DeadlineRequest(TaskCollection taskCollection, String requestString) throws UserException {
        super(taskCollection);
        this.deadline = DeadlineRequest.parseDeadline(requestString);
    }

    /**
     * Gets the Action the duke.request.DeadlineRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new AddTask(this.deadline, this.taskCollection);
    }

    /**
     * Parses an input String to create a duke.task.Deadline into a duke.task.Deadline.
     * @param deadlineString The input String.
     * @return The duke.task.Deadline parsed from the input String.
     * @throws UserException If the deadlineString is invalid.
     */
    private static Deadline parseDeadline(String deadlineString) throws UserException {
        String[] substrings = deadlineString.split(DeadlineRequest.BY_DELIMITER, 2);

        if (substrings.length != 2) {
            String[] exceptionMessages = new String[]{
                "A request to create a deadline task must follow the format:",
                "  deadline <description> /by <datetime>"
            };
            throw new UserException(String.join(System.lineSeparator(), exceptionMessages));
        }

        String description = substrings[0];
        String byDateTime = substrings[1];
        return new Deadline(description, byDateTime);
    }
}
