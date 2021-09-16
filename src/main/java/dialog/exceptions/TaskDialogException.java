package dialog.exceptions;

/**
 * Exception to do with Task Dialog to be thrown when a Task Dialog has encountered illegal
 * behaviour. Such method currently includes only addTask in TaskDialog.
 */
public class TaskDialogException extends Exception {
    /**
     * Default constructor of TaskDialogException.
     *
     * @param errorMessage message to be stored in the TaskDialogException.
     */
    public TaskDialogException(String errorMessage) {
        super(errorMessage);
    }
}
