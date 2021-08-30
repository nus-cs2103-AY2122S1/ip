package duke.exceptions;

/**
 * Class of exception that handles marking task
 * with a wrong format of date
 */
public class WrongDateFormatException extends DukeException{
    
    /**
     * Instantiates a exception that is wrong in its date format
     */
    public WrongDateFormatException() {
        super("Date format needs to be of the form dd-MM-yyyy or dd/MM/yyyy!");
    }
}
