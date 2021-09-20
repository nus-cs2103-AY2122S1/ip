package duke.exception;

/**
 * This is an customised exception thrown when the operation on the taskList is
 * out of bound.
 */
public class IndexOutOfBoundException extends InvalidInputException {
    private int taskListSize;

    public IndexOutOfBoundException(String operation, int taskListSize) {
        super(operation);
        this.taskListSize = taskListSize;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " because the taskList only contains " + taskListSize + " elements";
    }
}
