package duke.request;

import duke.action.Action;
import duke.action.FindTasks;
import duke.task.TaskCollection;

/**
 * FindRequest represents a request from the user to find all the Tasks in the application that matches a
 * given String.
 */
public class FindRequest extends TaskCollectionRequest {
    private final String string;

    /**
     * Creates a FindRequest.
     * @param taskCollection The target TaskCollection.
     * @param requestString The request String.
     */
    protected FindRequest(TaskCollection taskCollection, String requestString) {
        super(taskCollection);
        this.string = requestString;
    }

    /**
     * Gets the Action the FindRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new FindTasks(this.string, this.taskCollection);
    }
}
