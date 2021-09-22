package duke.main;

/**
 * Represents exceptions raised in Duke.Duke.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2.
 */
public class DukeException extends Exception {
    private Exceptions exception;

    /**
     * Class constructor.
     *
     * @param exception the exception thrown.
     */
    public DukeException(Exceptions exception) {
        this.exception = exception;
    }

    /**
     * Enum of exceptions handled by DukeException.
     */
    public enum Exceptions {
        StringIndexOutOfBoundsException,
        ArrayIndexOutOfBoundsException,
        IndexOutOfBoundsException,
        IOException,
        DateTimeParseException,
        EXCEPTIONS;
    }
    /**
     * Returns a message notifying user of an error.
     *
     * @return error message.
     */
    @Override
    public String getMessage() {
        String message;
        String exceptionName = exception.toString().split(":")[0];
        switch (exception) {
        case StringIndexOutOfBoundsException:
        case ArrayIndexOutOfBoundsException:
            message = "☹ The duke.task description or duke.command is incomplete.:$\n";
            break;
        case IndexOutOfBoundsException:
            message = "☹ OH NO!!! The duke.task does not exist.>:(\n";
            break;
        case IOException:
            message = "☹ OH NO!!! I cannot find the file.\n";
            break;
        case DateTimeParseException:
            message = "Please enter a proper date and time format.";
            break;
        default:
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        return message;
    }
}
