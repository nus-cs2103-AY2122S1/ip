package duke.exceptions;

/**
 * This is a CompletedTaskException that extends DukeException.
 * This exception is thrown when the user attempts to mark an already complete task as done.
 */
public class CompletedTaskException extends DukeException {

    /**
     * This is the class field of CompletedTaskException.
     */
    private final int index;


    /**
     * This is the CompletedTaskException constructor.
     *
     * @param indexToMark An index representing the index of an already complete task.
     */
    public CompletedTaskException(int indexToMark) {
        super("");
        this.index = indexToMark;
    }

    @Override
    public String getMessage() {
        return String.format("OOPS! I think you have already completed task %d !", this.index);
    }

}
