package duke.exceptions;

/**
 * @@author Hang Zelin
 *
 * Exception class which deals with invalid input or errors.
 */
public class DukeException extends Exception {
    ExceptionType type;

    /**
     * Constructor that stores the ErrorMessage encountered in Duke programme.
     *
     * @param type, Error type for duke
     */
    public DukeException(ExceptionType type) {
        this.type = type;
    }

    public String getErrorMessage() {
        String errorMessage;
        errorMessage = switch (type){
            case UNKNOWN_OPERATION -> "OOPS!!! I'm sorry, but I don't know what that means :-(";
            case NO_TASK_ERROR -> "OOPS!!! The description for this task cannot be empty.";
            case DEADLINE_FORMAT_ERROR -> "OOPS!!! I'm sorry, but the format of deadline is wrong :-(";
            case WRONG_INDEX_ERROR -> "OOPS!!! I'm sorry, but the index is invalid :-(";
            case EVENT_FORMAT_ERROR -> "OOPS!!! I'm sorry, but the format of event is wrong :-(";
            case TELL_FORMAT_ERROR -> "OOPS!!! I'm sorry, but the format of tell is wrong :-(";
            case FILE_WRITE_ERROR -> "OOPS!!! Cannot write in data into Duke!!!";
            case FILE_READ_ERROR -> "OOPS!!! Cannot Read From Data!!!";
            default -> "OOPS!!! There is Something wrong in Duke!!!";
        };

        return errorMessage;
    }

}
