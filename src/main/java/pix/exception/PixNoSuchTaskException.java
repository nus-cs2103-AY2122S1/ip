package pix.exception;

/**
 * Triggers when there is no such task in the task list.
 */
public class PixNoSuchTaskException extends PixException {
    @Override
    public String getMessage() {
        return "There is no such Pix.task in the Pix.task list! Are you sure you can type?";
    }
}
