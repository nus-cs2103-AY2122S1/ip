import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * Represents exceptions raised in Duke.
 * 
 * @author Gordon Yit
 * @Version CS2103T, Semester 2.
 */
public class DukeException extends Exception{
    private String message;
    private Exception e;

    /**
     * Class constructor for duke exception class.
     */
    public DukeException(Exception e) {
        this.e = e;
    }
    
    /**
     * Returns the exception message.
     * @return exception message.
     */

    @Override
    public String getMessage() {
        if (e instanceof NumberFormatException) {
            message = "☹ OH NO!!! The task does not exist.>:(\n";
        } else if (e instanceof StringIndexOutOfBoundsException || 
                    e instanceof ArrayIndexOutOfBoundsException) {
            message = "☹ The task description or command is incomplete.:$\n";
        } else if (e instanceof IOException) {
            message = "☹ OH NO!!! I cannot find the file.\n";
        } else if (e instanceof DateTimeParseException) {
            message = "Please enter a proper date and time format.";
        } else {
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        return message;
    }
}
