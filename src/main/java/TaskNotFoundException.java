/**
 * This is a TaskNotFoundException that extends DukeException.
 */
public class TaskNotFoundException extends DukeException {

    private final int index;
    public TaskNotFoundException(int index) {
        super("☹ OOPS!!! There is no such task!");
        this.index = index;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + String.format("\n\tI can't seem to find the task at number %d !", this.index);
    }

}
