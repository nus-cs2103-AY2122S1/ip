/**
 * ToDoRequest represents a request from the user to create an ToDo in the application.
 */
public class ToDoRequest extends TaskCollectionRequest {
    private final ToDo toDo;

    /**
     * Creates a ToDoRequest.
     * @param taskCollection The target TaskCollection.
     * @param requestString The request String.
     */
    protected ToDoRequest(TaskCollection taskCollection, String requestString) {
        super(taskCollection);
        this.toDo = new ToDo(requestString);
    }

    /**
     * Gets the Action the ToDoRequest requests to execute.
     * @return The Action to be executed.
     */
    @Override
    public Action action() {
        return new AddTask(this.toDo, this.taskCollection);
    }
}
