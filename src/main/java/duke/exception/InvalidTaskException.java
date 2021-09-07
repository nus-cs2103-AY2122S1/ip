package duke.exception;

/**
 * This is a customised exception stands for user's input does not
 * follow the task's format.
 */
public class InvalidTaskException extends DukeException {
    private String taskType;

    /**
     * Constructor for creating an InvalidTaskException.
     *
     * @param taskType The type of task user want.
     */
    public InvalidTaskException(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Return the warning that the input is not a valid task.
     *
     * @return The warning that the input is not a valid task.
     */
    @Override
    public String getMessage() {
        return "Sorry, this is not a valid " + taskType + " task.";
    }

}
