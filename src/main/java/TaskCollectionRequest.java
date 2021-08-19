/**
 * A TaskCollectionRequest represents a Request made to a TaskCollection.
 */
public abstract class TaskCollectionRequest extends Request {
    protected TaskCollection taskCollection;

    /**
     * Creates a TaskCollectionRequest to a specified Collection.
     * @param taskCollection The Target TaskCollection.
     */
    protected TaskCollectionRequest(TaskCollection taskCollection) {
        this.taskCollection = taskCollection;
    }
}
