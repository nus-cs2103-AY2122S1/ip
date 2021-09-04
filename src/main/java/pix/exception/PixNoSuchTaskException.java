package pix.exception;

/**
 * Triggers when there is no such task in the task list.
 */
public class PixNoSuchTaskException extends PixException {
    @Override
    public String getMessage() {
        return "There is no such task in the task list! Are you sure you can type?";
    }
}
