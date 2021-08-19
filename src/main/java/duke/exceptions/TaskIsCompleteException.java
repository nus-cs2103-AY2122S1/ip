package duke.exceptions;

/**
 * This is a duke.exceptions.TaskIsCompleteException that extends duke.exceptions.DukeException.
 */
public class TaskIsCompleteException extends DukeException {

    private final int index;
    public TaskIsCompleteException(int indexToMark) {
        super("");
        this.index = indexToMark;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS! I think you have already completed task %d !☹", this.index);
    }

}
