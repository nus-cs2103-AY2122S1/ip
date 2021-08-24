package duke.request;

import duke.action.ListTasks;
import duke.action.Action;
import duke.task.TaskCollection;

/**
 * duke.request.ListRequest represents a request from the user to list all the Tasks in the application.
 */
public class ListRequest extends TaskCollectionRequest {
    /**
     * Creates a duke.request.ListRequest.
     * @param taskCollection The target duke.task.TaskCollection.
     */
    protected ListRequest(TaskCollection taskCollection) {
        super(taskCollection);
    }

    /**
     * Gets the Action the duke.request.ListRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new ListTasks(this.taskCollection);
    }
}
