package duke.exceptions;

/**
 * Class of exception that handles a
 *  wrong format of command to delete task
 */
public class WrongDeleteFormatException extends DukeException {

    /**
     * Instantiates a exception that is wrong 
     * in the command in deleting a task
     */
    public WrongDeleteFormatException() {
        super("Delete command requires a number!");
    }
}
