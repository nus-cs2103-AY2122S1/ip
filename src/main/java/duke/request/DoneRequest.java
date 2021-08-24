package duke.request;

import duke.task.Task;
import duke.task.TaskCollection;
import duke.exception.UserException;
import duke.action.Action;
import duke.action.CompleteTask;

import java.lang.NumberFormatException;

/**
 * duke.request.DoneRequest represents a request from the user to mark a duke.task.Task as done in the application.
 */
public class DoneRequest extends TaskCollectionRequest {
    private final int taskId;

    /**
     * Creates a duke.request.DoneRequest.
     * @param taskCollection The target duke.task.TaskCollection.
     * @param requestString The request String.
     * @throws UserException If the request String is invalid.
     */
    protected DoneRequest(TaskCollection taskCollection, String requestString) throws UserException {
        super(taskCollection);
        try {
            this.taskId = Integer.parseInt(requestString);
        } catch (NumberFormatException exception) {
            throw new UserException("The task number of your done request is either missing or invalid.");
        }
    }

    /**
     * Gets the Action the duke.request.DoneRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        Task task = this.taskCollection.get(this.taskId);
        return new CompleteTask(task);
    }
}
