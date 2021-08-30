package duke.exceptions;

/**
 * Class of exception that handles a
 *  wrong format of command to mark task as done
 */
public class WrongDoneFormatException extends DukeException{

    /**
     * Instantiates a exception that is wrong 
     * in the format of command in marking task as done
     */
    public WrongDoneFormatException() {
        super("Done command requires a number!");
    }
}
