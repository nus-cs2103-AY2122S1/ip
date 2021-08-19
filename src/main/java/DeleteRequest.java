import java.lang.NumberFormatException;

/**
 * DeleteRequest represents a request from the user to delete a Task from the TaskCollection.
 */
public class DeleteRequest extends TaskCollectionRequest {
    private final int taskId;

    /**
     * Creates a DeleteRequest.
     * @param taskCollection The target TaskCollection.
     * @param requestString The request String.
     * @throws UserException If the request String is invalid.
     */
    protected DeleteRequest(TaskCollection taskCollection, String requestString) throws UserException {
        super(taskCollection);
        try {
            this.taskId = Integer.parseInt(requestString);
        } catch (NumberFormatException exception) {
            throw new UserException("The task number of your delete request is either missing or invalid.");
        }
    }

    /**
     * Gets the Action the DeleteRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new DeleteTask(this.taskId, this.taskCollection);
    }
}
