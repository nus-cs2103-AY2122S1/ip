/**
 * ListRequest represents a request from the user to list all the Tasks in the application.
 */
public class ListRequest extends TaskCollectionRequest {
    /**
     * Creates a ListRequest.
     * @param taskCollection The target TaskCollection.
     */
    protected ListRequest(TaskCollection taskCollection) {
        super(taskCollection);
    }

    /**
     * Gets the Action the ListRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new ListTasks(this.taskCollection);
    }
}
