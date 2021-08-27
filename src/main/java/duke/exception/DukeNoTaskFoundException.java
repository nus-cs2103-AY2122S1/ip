package duke.exception;

/**
 * Represents a duke exception when a task cannot be found in database.
 */
public class DukeNoTaskFoundException extends DukeException {
    private int taskNum;

    /**
     * Constructor for the DukeNoTaskFoundException.
     *
     * @param taskNum the number of task in the
     */
    public DukeNoTaskFoundException(int taskNum) {
        this.taskNum = taskNum;
    }


    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s I cannot find the task with number %d!",
                super.toString(),
                this.taskNum
        );
    }
}
