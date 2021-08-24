package duke.request;

import duke.action.DeleteTask;
import duke.task.TaskCollection;
import duke.exception.UserException;
import duke.action.Action;

import java.lang.NumberFormatException;

/**
 * duke.request.DeleteRequest represents a request from the user to delete a duke.task.Task from the duke.task.TaskCollection.
 */
public class DeleteRequest extends TaskCollectionRequest {
    private final int taskId;

    /**
     * Creates a duke.request.DeleteRequest.
     * @param taskCollection The target duke.task.TaskCollection.
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
     * Gets the Action the duke.request.DeleteRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new DeleteTask(this.taskId, this.taskCollection);
    }
}
