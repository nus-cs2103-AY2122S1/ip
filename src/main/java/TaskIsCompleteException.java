/**
 * This is a TaskIsCompleteException that extends DukeException.
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
