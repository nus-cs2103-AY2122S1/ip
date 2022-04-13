package hyddd.exceptions;

/**
 * @@author Hang Zelin
 *
 * Exception class which deals with invalid input or errors.
 */
public class HydddException extends Exception {
    private final ExceptionType type;

    /**
     * Constructor that stores the ErrorMessage encountered in Duke programme.
     *
     * @param type Error type for duke
     */
    public HydddException(ExceptionType type) {
        this.type = type;
    }

    /**
     * Returns Duke's response for an exception issue.
     *
     * @return ErrorMessage for a specific DukeException Type.
     */
    public String getErrorMessage() {
        String errorMessage;
        switch (type) {
        case UNKNOWN_OPERATION: errorMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        break;
        case NO_TASK_ERROR: errorMessage = "OOPS!!! The description for this task cannot be empty.";
        break;
        case DEADLINE_FORMAT_ERROR: errorMessage = "OOPS!!! I'm sorry, but the format of deadline is wrong :-(";
        break;
        case WRONG_INDEX_ERROR: errorMessage = "OOPS!!! I'm sorry, but the index is invalid :-(";
        break;
        case EVENT_FORMAT_ERROR: errorMessage = "OOPS!!! I'm sorry, but the format of event is wrong :-(";
        break;
        case TELL_FORMAT_ERROR: errorMessage = "OOPS!!! I'm sorry, but the format of tell is wrong :-(";
        break;
        case EMPTY_COMMAND_ERROR: errorMessage = "OOPS!!! Sorry but I can't receive anything from you!!!";
        break;
        case DUPLICATE_TASK_ERROR: errorMessage = "OOPS!!! Sorry, but the task is undone in your taskList!!!";
        break;
        case FILE_WRITE_ERROR: errorMessage = "OOPS!!! Cannot write in data into Duke!!!";
        break;
        case FILE_READ_ERROR: errorMessage = "OOPS!!! Cannot Read From Data!!!";
        break;
        default: errorMessage = "OOPS!!! There is Something wrong in Duke!!!";
        break;
        }
        return errorMessage;
    }

}
