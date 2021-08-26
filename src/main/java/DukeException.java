import java.io.IOException;
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
<<<<<<< HEAD
    @Override
=======
>>>>>>> branch-Level-8
    public String getMessage() {
        if (e instanceof ArrayIndexOutOfBoundsException) {
            message = "☹ OH NO!!! The task does not exist.>:(\n";
        } else if (e instanceof StringIndexOutOfBoundsException) {
<<<<<<< HEAD
            message = "☹ The task description or command is incomplete.:$\n";
        } else if (e instanceof IOException) {
            message = "☹ OH NO!!! I cannot find the file.\n";
        } else {
                message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
=======
            message = "☹ OOPS!!! The task description or command is incomplete.\n";
        } else if (e instanceof DateTimeParseException) {
            message = "Please enter a proper date and time format.";
        }else {
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
>>>>>>> branch-Level-8
        }
        return message;
    }
}
