package duke.exceptions;

/**
 * Class of exception that handles 
 * command unknown to the duke bot
 */
public class NoSuchCommandException extends DukeException {

    /**
     * Instantiates a NoSuchCommand exception
     * 
     * @param errorMsg String error message of the unknown command
     */
    public NoSuchCommandException(String errorMsg) {
        super(errorMsg);
    }
}
