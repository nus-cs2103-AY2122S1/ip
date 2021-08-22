package duke.exceptions;

/**
 * This is a TaskIsCompleteException that extends DukeException.
 * This exception is thrown when the user attempts to mark an already complete task as done.
 */
public class TaskIsCompleteException extends DukeException {

    /**
     * This is the class field of TaskIsCompleteException.
     */
    private final int index;


    /**
     * This is the TaskIsCompleteException constructor.
     *
     * @param indexToMark An index representing the index of an already complete task.
     */
    public TaskIsCompleteException(int indexToMark) {
        super("");
        this.index = indexToMark;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS! I think you have already completed task %d !☹", this.index);
    }

}
