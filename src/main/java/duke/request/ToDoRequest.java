package duke.request;

import duke.action.Action;
import duke.action.AddTask;
import duke.exception.UserException;
import duke.task.TaskCollection;
import duke.task.ToDo;

/**
 * duke.request.ToDoRequest represents a request from the user to create an duke.task.ToDo in the application.
 */
public class ToDoRequest extends TaskCollectionRequest {
    private final ToDo toDo;

    /**
     * Creates a duke.request.ToDoRequest.
     * @param taskCollection The target duke.task.TaskCollection.
     * @param requestString The request String.
     * @throws UserException If the request String is invalid.
     */
    protected ToDoRequest(TaskCollection taskCollection, String requestString) throws UserException {
        super(taskCollection);

        if (requestString.isEmpty()) {
            throw new UserException("The description of a todo cannot be empty.");
        }

        this.toDo = new ToDo(requestString);
    }

    /**
     * Gets the Action the duke.request.ToDoRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new AddTask(this.toDo, this.taskCollection);
    }
}
