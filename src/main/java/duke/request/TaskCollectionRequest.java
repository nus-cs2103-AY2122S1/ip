package duke.request;

import duke.task.TaskCollection;

/**
 * A duke.request.TaskCollectionRequest represents a duke.request.Request made to a duke.task.TaskCollection.
 */
public abstract class TaskCollectionRequest extends Request {
    protected TaskCollection taskCollection;

    /**
     * Creates a duke.request.TaskCollectionRequest to a specified Collection.
     * @param taskCollection The Target duke.task.TaskCollection.
     */
    protected TaskCollectionRequest(TaskCollection taskCollection) {
        this.taskCollection = taskCollection;
    }
}
