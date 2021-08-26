import java.time.format.DateTimeParseException;

/**
 * Represents exceptions raised in Duke.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */
public class DukeException extends Exception{
    private String message;
    private Exception e;

    /**
     * Class constructor for duke exception class.
     * @param e the exception raised in Duke class.
     */
    public DukeException(Exception e) {
        this.e = e;
    }

    /**
     * Returns the exception message.
     * @return exception message.
     */
    public String getMessage() {
        if (e instanceof ArrayIndexOutOfBoundsException) {
            message = "☹ OOPS!!! The task does not exist.\n";
        } else if (e instanceof StringIndexOutOfBoundsException) {
            message = "☹ OOPS!!! The task description or command is incomplete.\n";
        } else if (e instanceof DateTimeParseException) {
            message = "Please enter a proper date and time format.";
        }else {
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        return message;
    }
}
