/**
 * DoneRequest represents a request from the user to mark a Task as done in the application.
 */
public class DoneRequest extends TaskCollectionRequest {
    private final int taskId;

    /**
     * Creates a DoneRequest.
     * @param taskCollection The target TaskCollection.
     * @param requestString The request String.
     */
    protected DoneRequest(TaskCollection taskCollection, String requestString) {
        super(taskCollection);
        this.taskId = Integer.parseInt(requestString);
    }

    /**
     * Gets the Action the DoneRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        Task task = this.taskCollection.get(this.taskId);
        return new CompleteTask(task);
    }
}
